package com.app.contacts.data.source.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

internal const val TABLE_CONTACT = "contact"
internal const val TABLE_EMAIL = "emails"
internal const val TABLE_PHONE = "phoneNumbers"
internal const val DB_CONTACT = "Contact_DB"

@Entity(tableName = TABLE_CONTACT)
data class SourceContact(
    @PrimaryKey val contactId: String,
    val name: String,
)

@Entity(
    tableName = TABLE_PHONE,
    foreignKeys =
        arrayOf(
            ForeignKey(
                entity = SourceContact::class,
                parentColumns = arrayOf("contactId"),
                childColumns = arrayOf("contactId"),
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE,
            ),
        ),
)
data class SourcePhoneNumber(
    @PrimaryKey val phoneNumber: String,
    val contactId: String,
)

@Entity(
    tableName = TABLE_EMAIL,
    foreignKeys =
        arrayOf(
            ForeignKey(
                entity = SourceContact::class,
                parentColumns = arrayOf("contactId"),
                childColumns = arrayOf("contactId"),
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE,
            ),
        ),
)
data class SourceEmail(
    @PrimaryKey val email: String,
    val contactId: String,
)

data class SourceContactWithDetails(
    @Embedded val contact: SourceContact,
    @Relation(
        parentColumn = "contactId",
        entityColumn = "contactId",
    )
    val phoneNumbers: List<SourcePhoneNumber>,
    @Relation(
        parentColumn = "contactId",
        entityColumn = "contactId",
    )
    val emails: List<SourceEmail>,
)
