package com.app.contacts.data.source

import android.content.Context
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds
import android.provider.ContactsContract.Contacts
import com.app.contacts.data.api.ContactRepository
import com.app.contacts.data.api.ContactWriteRepository
import com.app.contacts.data.api.PhoneAndEmailRepository
import com.app.contacts.data.source.model.SourceContact
import com.app.contacts.data.source.model.SourceEmail
import com.app.contacts.data.source.model.SourcePhoneNumber
import com.app.contacts.init.SingletonFactory

class SourceContactRepository(
    private val context: Context,
) : ContactRepository<SourceContact>,
    ContactWriteRepository<SourceContact, SourcePhoneNumber, SourceEmail>,
    PhoneAndEmailRepository<SourcePhoneNumber, SourceEmail> {
    private val contactDao by lazy {
        SingletonFactory.getContactDao()
    }

    override suspend fun getAllContact(): Result<List<SourceContact>> {
        return try {
            val contacts = ArrayList<SourceContact>()
            context.contentResolver.query(
                Contacts.CONTENT_URI,
                null,
                null,
                null,
                null,
            )?.use {
                if (it.count > 0) {
                    val colIndexContactId = it.getColumnIndex(Contacts._ID)
                    val colIndexContactName = it.getColumnIndex(Contacts.DISPLAY_NAME)

                    while (it.moveToNext()) {
                        val contactId = it.getString(colIndexContactId)
                        val contactName = it.getString(colIndexContactName)

                        if (contactId != null && contactName != null) {
                            contacts.add(SourceContact(contactId, contactName))
                        }
                    }
                }
            }

            Result.success(contacts)
        } catch (exe: Exception) {
            Result.failure(exe)
        }
    }

    override fun writeContacts(contacts: List<SourceContact>) {
    }

    override fun writePhoneNumbers(phoneNumbers: List<SourcePhoneNumber>) {
    }

    override fun writeEmailIds(emailIds: List<SourceEmail>) {
    }

    override fun getAllPhoneNumbers(): Result<List<SourcePhoneNumber>> {
        return try {
            val phoneNumbers = ArrayList<SourcePhoneNumber>()
            context.contentResolver.query(
                CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null,
            )?.use {
                if (it.count > 0) {
                    val columnIndexContactId =
                        it.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)
                    val columnIndexContactPhoneNumber =
                        it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

                    while (it.moveToNext()) {
                        val contactId = it.getString(columnIndexContactId)
                        val phoneNumber = it.getString(columnIndexContactPhoneNumber)

                        phoneNumbers.add(
                            SourcePhoneNumber(
                                phoneNumber,
                                contactId,
                            ),
                        )
                    }
                }
            }

            Result.success(phoneNumbers)
        } catch (exe: Exception) {
            Result.failure(exe)
        }
    }

    override fun getAllEmails(): Result<List<SourceEmail>> {
        return try {
            val emails = ArrayList<SourceEmail>()

            context.contentResolver.query(
                CommonDataKinds.Email.CONTENT_URI,
                null,
                null,
                null,
                null,
            )?.use {
                if (it.count > 0) {
                    val columnIndexContactId = it.getColumnIndex(CommonDataKinds.Email._ID)
                    val columnIndexEmail = it.getColumnIndex(CommonDataKinds.Email.DISPLAY_NAME)

                    while (it.moveToNext()) {
                        val contactId = it.getString(columnIndexContactId)
                        val contactEmail = it.getString(columnIndexEmail)

                        if (contactId != null && contactEmail != null) {
                            emails.add(
                                SourceEmail(
                                    contactEmail,
                                    contactId,
                                ),
                            )
                        }
                    }
                }
            }

            return Result.success(emails)
        } catch (exe: Exception) {
            Result.failure(exe)
        }
    }
}
