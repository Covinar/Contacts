package com.example.presentation.newcontact

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.presentation.models.ContactModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContactTopBar(
    isNumberLineEmpty: Boolean,
    contact: ContactModel?,
    onCreateClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {

    Column {
        TopAppBar(
            title = {
                val stringId =
                    if (contact == null) R.string.new_contact_screen_top_bar_title
                    else R.string.new_contact_screen_top_bar_edit_title
                Text(
                    text = stringResource(id = stringId),
                    fontWeight = FontWeight.Bold
                )
            },
            actions = {
                Button(
                    onClick = {
                        if (!isNumberLineEmpty) {
                            onCreateClicked()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = if (isNumberLineEmpty) Color.Gray else Color.White,
                        containerColor = if (isNumberLineEmpty) Color.LightGray else Color.Black
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.create_button),
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            navigationIcon = {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(18.dp)
                        .height(18.dp)
                        .clickable {
                            onCloseClicked()
                        },
                    painter = painterResource(id = R.drawable.ic_close),
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