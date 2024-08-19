package com.example.domain.usecases

import com.example.domain.models.Contact
import kotlinx.coroutines.flow.Flow

interface GetContactsUseCase {

    operator fun invoke(): Flow<List<Contact>>

}