package com.example.data.di

import android.content.Context
import com.example.data.repos.ContactsRepositoryImpl
import com.example.domain.repos.ContactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideContactsRepository(@ApplicationContext context: Context): ContactsRepository = ContactsRepositoryImpl(context)

}