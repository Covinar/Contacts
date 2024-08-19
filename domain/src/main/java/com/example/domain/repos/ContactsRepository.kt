package com.example.domain.repos

import com.example.domain.models.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

    fun getContacts(): Flow<List<Contact>>

    fun createContact(contact: Contact)

}