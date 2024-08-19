package com.example.presentation.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.models.ContactModel
import com.example.presentation.utils.DashedHorizontalDivider

@Composable
fun ContactItem(
    contact: ContactModel,
    isLast: Boolean,
    onClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 12.dp)
            .clickable {
                onClicked()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .background(Color.White)
        ) {
            Column() {
                Row() {
                    Text(
                        text = contact.name,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "  "
                    )
                    Text(
                        text = contact.surname,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
        if(!isLast) {
            DashedHorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }


}