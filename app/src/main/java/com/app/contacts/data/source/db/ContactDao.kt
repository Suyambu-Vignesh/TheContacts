package com.app.contacts.data.source.db

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.app.contacts.data.source.model.SourceContact
import com.app.contacts.data.source.model.SourceContactWithDetails
import com.app.contacts.data.source.model.SourceEmail
import com.app.contacts.data.source.model.SourcePhoneNumber
import com.app.contacts.data.source.model.TABLE_CONTACT
import com.app.contacts.data.source.model.TABLE_EMAIL

interface ContactDao {

    @Insert
    fun addAllContacts(
        contacts: List<SourceContact>
    )

    @Insert
    fun addAllPhoneNumbers(
        contacts: List<SourcePhoneNumber>
    )

    @Insert
    fun addAllEmais(
        contacts: List<SourceEmail>
    )

    @Transaction
    @Query("SELECT * FROM $TABLE_CONTACT ORDER BY name")
    fun getAllContactWithDetail(): PagingSource<Int, SourceContactWithDetails>
}