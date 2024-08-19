package com.example.presentation.newcontact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.models.ContactModel
import com.example.presentation.utils.DashedHorizontalDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContactScreen(
    viewModel: NewContactViewModel = hiltViewModel(),
    contact: ContactModel?,
    onCloseClicked: () -> Unit
) {

    var number by remember { mutableStateOf(contact?.numbers?.getOrNull(0) ?: "") }
    var name by remember { mutableStateOf(contact?.name ?: "") }
    var surname by remember { mutableStateOf(contact?.surname ?: "") }

    Scaffold(
        topBar = {
            NewContactTopBar(
                isNumberLineEmpty = number.isEmpty(),
                onCreateClicked = {
                    viewModel.createContact()
                    onCloseClicked()
                },
                onCloseClicked = {
                    onCloseClicked()
                },
                contact = contact
            )
        },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Column() {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = number,
                    onValueChange = {
                        number = it
                        viewModel.updateNumber(listOf(it))
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.new_contact_screen_phone_number_hint)
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                DashedHorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp)
                )
            }
            Column(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = stringResource(id = R.string.new_contact_screen_first_name),
                    fontWeight = FontWeight.Bold
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                        viewModel.updateFirstName(it)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true
                )
                DashedHorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = stringResource(id = R.string.new_contact_screen_last_name),
                    fontWeight = FontWeight.Bold
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = surname,
                    onValueChange = {
                        surname = it
                        viewModel.updateLastName(it)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                )
            }
        }
    }

}