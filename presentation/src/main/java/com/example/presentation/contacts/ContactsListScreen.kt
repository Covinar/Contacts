package com.example.presentation.contacts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.models.Contact
import com.example.presentation.models.ContactModel

@Composable
fun ContactsListScreen(
    contacts: List<ContactModel>,
    onContactClicked: (contact: ContactModel) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        itemsIndexed(
            items = contacts,
            key = { _, contact ->
                contact.id
            }
        ) {index, contact ->
            ContactItem(
                contact = contact,
                isLast = index == contacts.size - 1,
                onClicked = {
                    onContactClicked(contact)
                }
            )
        }

    }

}