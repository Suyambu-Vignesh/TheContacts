package com.app.contacts.init

import android.content.Context
import androidx.room.Room
import com.app.contacts.data.api.ContactRepository
import com.app.contacts.data.api.ContactWriteRepository
import com.app.contacts.data.api.PhoneAndEmailRepository
import com.app.contacts.data.source.SourceContactRepository
import com.app.contacts.data.source.db.ContactDao
import com.app.contacts.data.source.db.ContactDatabase
import com.app.contacts.data.source.model.SourceContact
import com.app.contacts.data.source.model.SourceEmail
import com.app.contacts.data.source.model.SourcePhoneNumber

object SingletonFactory {
    // todo bad one use Hilt. Cannot control where we init
    private lateinit var sourceContactRepository: SourceContactRepository
    private lateinit var contactDao: ContactDao

    fun init(context: Context) {
        sourceContactRepository =
            SourceContactRepository(
                context,
            )

        contactDao =
            Room.databaseBuilder(
                context,
                ContactDatabase::class.java,
                "database",
            ).build().getContactDao()
    }

    fun getContactRepository(): ContactRepository<Result<List<SourceContact>>> {
        return sourceContactRepository
    }

    internal fun getPhoneAndEmailRepository(): PhoneAndEmailRepository<SourcePhoneNumber, SourceEmail> {
        return sourceContactRepository
    }

    fun getContactWriteRepository(): ContactWriteRepository<SourceContact, SourcePhoneNumber, SourceEmail> {
        return sourceContactRepository
    }

    fun getContactDao(): ContactDao {
        return contactDao
    }
}
