package com.example.presentation.contacts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.R

@Composable
fun EmptyContacts(
    modifier: Modifier = Modifier,
    onCreateClicked: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = stringResource(id = R.string.empty_contacts_title),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = stringResource(id = R.string.empty_contacts_description),
            maxLines = 3,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.padding(4.dp),
            shape = AbsoluteRoundedCornerShape(8.dp),
            border = BorderStroke(
                color = Color.Black,
                width = 2.dp
            ),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.White
            ),
            onClick = {
                onCreateClicked()
            }
        ) {
            Text(
                text = stringResource(id = R.string.empty_contacts_button),
                fontWeight = FontWeight.Bold
            )
        }
    }

}