package com.example.presentation.newcontact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Contact
import com.example.domain.usecases.CreateContactUseCase
import com.example.presentation.mappers.toContact
import com.example.presentation.models.ContactModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewContactViewModel @Inject constructor(
    private val createContactUseCase: CreateContactUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun updateNumber(numbers: List<String>) {
        val newList = numbers.toList()
        val contact = state.value.contact.copy(numbers = newList)
        _state.update {
            it.copy(contact = contact)
        }
    }

    fun updateFirstName(firstName: String) {
        val contact = state.value.contact.copy(name = firstName)
        _state.update {
            it.copy(contact = contact)
        }
    }

    fun updateLastName(lastName: String) {
        val contact = state.value.contact.copy(surname = lastName)
        _state.update {
            it.copy(contact = contact)
        }
    }

    fun createContact() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value.contact.let {
                createContactUseCase(it.toContact())
            }
            withContext(Dispatchers.Main) {
                _state.update { State() }
            }
        }
    }

    data class State(
        val contact: ContactModel = ContactModel(0, "", "", emptyList())
    )

}