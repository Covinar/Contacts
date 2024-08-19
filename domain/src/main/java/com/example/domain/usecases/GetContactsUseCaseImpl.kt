package com.example.domain.usecases

import com.example.domain.models.Contact
import com.example.domain.repos.ContactsRepository
import kotlinx.coroutines.flow.Flow

class GetContactsUseCaseImpl(
    private val contactsRepository: ContactsRepository
): GetContactsUseCase {

    override fun invoke(): Flow<List<Contact>> {
        return contactsRepository.getContacts()
    }

}