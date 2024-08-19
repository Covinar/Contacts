package com.example.domain.di

import com.example.domain.repos.ContactsRepository
import com.example.domain.usecases.CreateContactUseCase
import com.example.domain.usecases.CreateContactUseCaseImpl
import com.example.domain.usecases.GetContactsUseCase
import com.example.domain.usecases.GetContactsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetContactsUseCase(contactsRepository: ContactsRepository): GetContactsUseCase = GetContactsUseCaseImpl(contactsRepository)

    @Provides
    fun provideCreateContactUseCase(contactsRepository: ContactsRepository): CreateContactUseCase =CreateContactUseCaseImpl(contactsRepository)

}