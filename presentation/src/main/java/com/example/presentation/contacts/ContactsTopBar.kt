package com.example.presentation.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsTopBar(
    onCreateClicked: () -> Unit
) {

    Column(
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.contacts_top_bar_title),
                    fontWeight = FontWeight.Bold
                )
            },
            actions = {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = null
                )
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {
                            onCreateClicked()
                        },
                    painter = painterResource(id = R.drawable.ic_add_contact),
                    contentDescription = null
                )
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            )
        )
        Divider(
            thickness = 2.dp,
            color = Color.Black
        )
    }


}