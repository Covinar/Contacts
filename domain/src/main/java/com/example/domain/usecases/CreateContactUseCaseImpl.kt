package com.example.domain.usecases

import com.example.domain.models.Contact
import com.example.domain.repos.ContactsRepository

class CreateContactUseCaseImpl(
    private val contactsRepository: ContactsRepository
): CreateContactUseCase {

    override fun invoke(contact: Contact) {
        contactsRepository.createContact(contact)
    }

}