package com.example.presentation.main

sealed class Screen(
    val route: String
) {

    data object Contacts: Screen(CONTACTS_ROUTE)

    data object ContactDetails: Screen(NEW_CONTACT_ROUTE) {
        const val ARGS_CONTACT = "/{contact}"
        const val CONTACT = "contact"
    }

    companion object {
        private const val CONTACTS_ROUTE = "contacts_route"
        private const val NEW_CONTACT_ROUTE = "new_contact"
    }

}