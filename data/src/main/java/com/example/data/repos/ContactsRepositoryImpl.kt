package com.example.data.repos

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import android.provider.ContactsContract.RawContacts
import com.example.domain.models.Contact
import com.example.domain.repos.ContactsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ContactsRepositoryImpl(
    private val context: Context
): ContactsRepository {

    override fun getContacts(): Flow<List<Contact>> = callbackFlow {
        trySend(getContactsList())
        val contactsObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {

            override fun onChange(selfChange: Boolean) {
                super.onChange(selfChange)
                val contacts = getContactsList()
                trySend(contacts)
            }

        }
        context.contentResolver.registerContentObserver(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, false, contactsObserver)

        awaitClose {
            context.contentResolver.unregisterContentObserver(contactsObserver)
        }
    }

    private fun getContactsList() : List<Contact> {
        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
        )
        val contacts: MutableSet<Contact> = mutableSetOf()
        while (cursor?.moveToNext() == true) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
            val contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME_PRIMARY))
            val name = StringBuilder()
            val surname = StringBuilder()
            val splittedName = contactName.split(" ")
            if (splittedName.size == 1) {
                name.append(contactName)
            } else {
                surname.append(splittedName.last())
                name.append(contactName.substringBeforeLast( " "))
            }
            val number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val formattedNumber = number.replace(" ", "")
            val temp = Contact(id , name.toString(), surname.toString(), emptyList())
            if (contacts.contains(temp)) {
                val contact = contacts.find { it.id == id } ?: throw IllegalStateException("Wrong hash function")
                val numbers = contact.numbers.toMutableSet().apply { add(formattedNumber) }
                contacts.remove(temp)
                contacts.add(contact.copy(numbers = numbers.toList()))
            } else {
                contacts.add(Contact(id , name.toString(), surname.toString(), listOf(formattedNumber)))
            }
        }
        cursor?.close()
        return contacts.sortedBy { it.name }.toList()
    }

    override fun createContact(contact: Contact) {
        val contentValues = ContentValues()
        val rawContactUri = context.contentResolver.insert(RawContacts.CONTENT_URI, contentValues)
        val rawContactId = ContentUris.parseId(rawContactUri!!)
        contentValues.clear()
        contentValues.apply {
            put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
            put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contact.name + " " + contact.surname)
        }
        context.contentResolver.insert(ContactsContract.Data.CONTENT_URI, contentValues)
        contentValues.clear()
        contentValues.apply {
            put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
            put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
            put(ContactsContract.CommonDataKinds.Phone.NUMBER, contact.numbers[0])
            put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
        }
        context.contentResolver.insert(ContactsContract.Data.CONTENT_URI, contentValues)
    }

}