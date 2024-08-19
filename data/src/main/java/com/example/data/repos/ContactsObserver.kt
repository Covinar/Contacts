package com.example.data.repos

import android.database.ContentObserver
import android.os.Handler
import android.os.Looper

class ContactsObserver: ContentObserver(Handler(Looper.getMainLooper())) {

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
    }

}