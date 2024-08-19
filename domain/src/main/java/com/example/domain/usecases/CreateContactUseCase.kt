package com.example.domain.usecases

import com.example.domain.models.Contact

interface CreateContactUseCase {

    operator fun invoke(contact: Contact)

}