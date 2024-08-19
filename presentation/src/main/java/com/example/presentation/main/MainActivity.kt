package com.example.presentation.main

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presentation.contacts.ContactsScreen
import com.example.presentation.models.ContactModel
import com.example.presentation.newcontact.NewContactScreen
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        isGranted = it
    }

    private val permissionWriteContactsLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        isWriteGranted = it
    }

    private var isGranted by mutableStateOf(false)

    private var isWriteGranted by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screen.Contacts.route
            ) {
                composable(route = Screen.Contacts.route) {
                    if (isGranted) {
                        ContactsScreen(
                            onCreateClicked = {
                                navController.navigate("${Screen.ContactDetails.route}/${null}")
                            },
                            onOpenContact = { contact ->
                                val json = Uri.encode(Gson().toJson(contact))
                                navController.navigate("${Screen.ContactDetails.route}/$json")
                            }
                        )
                    } else {
                        permissionLauncher.launch(android.Manifest.permission.READ_CONTACTS)
                    }
                }
                composable(
                    route = Screen.ContactDetails.route + Screen.ContactDetails.ARGS_CONTACT,
                    arguments = listOf(
                        navArgument(name = Screen.ContactDetails.CONTACT) {
                            defaultValue = null
                            type = ContactNavType()
                            nullable = true
                        }
                    )
                ) { backStackEntry ->
                    if (isWriteGranted) {
                        val contact = backStackEntry.arguments?.getParcelable<ContactModel>(Screen.ContactDetails.CONTACT)
                        NewContactScreen(
                            onCloseClicked = {
                                navController.navigateUp()
                            },
                            contact = contact
                        )
                    } else {
                        permissionWriteContactsLauncher.launch(android.Manifest.permission.WRITE_CONTACTS)
                    }
                }
            }

        }
    }
}