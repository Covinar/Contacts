package com.example.presentation.main

import android.os.Bundle
import androidx.navigation.NavType
import com.example.presentation.models.ContactModel
import com.google.gson.Gson

class ContactNavType: NavType<ContactModel?>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): ContactModel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ContactModel? {
        return Gson().fromJson(value, ContactModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ContactModel?) {
        bundle.putParcelable(key, value)
    }
}