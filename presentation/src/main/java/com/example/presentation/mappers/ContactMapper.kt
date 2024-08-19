package com.example.presentation.mappers

import com.example.domain.models.Contact
import com.example.presentation.models.ContactModel

fun Contact.toContactModel() = ContactModel(id, name, surname, numbers)

fun List<Contact>.toContactModels() = this.map { it.toContactModel() }

fun ContactModel.toContact() = Contact(id, name, surname, numbers)