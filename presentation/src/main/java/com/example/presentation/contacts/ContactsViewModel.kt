package com.example.presentation.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Contact
import com.example.domain.usecases.GetContactsUseCase
import com.example.presentation.mappers.toContactModels
import com.example.presentation.models.ContactModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun getContacts() {
        getContactsUseCase()
            .flowOn(Dispatchers.IO)
            .onEach { contacts ->
                _state.update {
                    it.copy(contacts = contacts.toContactModels())
                }
            }.launchIn(viewModelScope)
    }

    data class State(
        val contacts: List<ContactModel> = emptyList()
    )

}