package com.app.contacts.data.source.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.app.contacts.data.source.model.SourceContact
import com.app.contacts.data.source.model.SourceContactWithDetails
import com.app.contacts.data.source.model.SourceEmail
import com.app.contacts.data.source.model.SourcePhoneNumber
import com.app.contacts.data.source.model.TABLE_CONTACT

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllContacts(contacts: List<SourceContact>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllPhoneNumbers(contacts: List<SourcePhoneNumber>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllEmails(contacts: List<SourceEmail>)

    @Transaction
    @Query("SELECT * FROM $TABLE_CONTACT")
    fun getAllContactWithDetail(): PagingSource<Int, SourceContactWithDetails>
}
