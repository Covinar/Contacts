package com.example.presentation.contacts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.models.ContactModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = hiltViewModel(),
    onCreateClicked: () -> Unit,
    onOpenContact: (contact: ContactModel) -> Unit
) {

    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 ="contacts_screen") {
        viewModel.getContacts()
    }

    Scaffold(
        topBar = {
            ContactsTopBar(
                onCreateClicked = {
                    onCreateClicked()
                }
            )
        },
        containerColor = Color.White
    ) {
        if (state.value.contacts.isEmpty()) {
            EmptyContacts(
                modifier = Modifier.padding(it),
                onCreateClicked = {

                }
            )
        } else {
            ContactsListScreen(
                modifier = Modifier.padding(it),
                contacts = state.value.contacts,
                onContactClicked = { contact ->
                    onOpenContact(contact)
                }
            )
        }
    }

}