package com.example.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactModel(
    val id: Int,
    val name: String,
    val surname: String,
    val numbers: List<String>
): Parcelable
