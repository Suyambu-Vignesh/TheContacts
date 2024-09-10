package com.app.contacts.data.source

import android.content.Context
import androidx.room.Room
import com.app.contacts.data.source.db.ContactDao
import com.app.contacts.data.source.db.ContactDatabase
import com.app.contacts.data.source.model.DB_CONTACT

class SourceFactory {
    companion object {
        private var db: ContactDatabase? = null

        fun getContactDatabase(context: Context): ContactDao {
            return (
                db ?: run {
                    val newDB =
                        Room.databaseBuilder(context, ContactDatabase::class.java, DB_CONTACT)
                            .build()
                    db = newDB
                    newDB
                }
            ).getContactDao()
        }
    }
}
