package com.app.contacts.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.contacts.data.source.model.SourceContact
import com.app.contacts.data.source.model.SourceEmail
import com.app.contacts.data.source.model.SourcePhoneNumber

@Database(
    version = 1,
    entities = [SourceContact::class, SourcePhoneNumber::class, SourceEmail::class],
)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun getContactDao(): ContactDao
}
