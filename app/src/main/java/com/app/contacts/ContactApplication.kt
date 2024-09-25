package com.app.contacts

import android.app.Application
import com.app.contacts.init.SingletonFactory

class ContactApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SingletonFactory.init(this)
    }
}
