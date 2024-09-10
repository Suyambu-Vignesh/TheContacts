package com.app.contacts.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.app.contacts.data.api.ContactRepository
import com.app.contacts.data.api.ContactWriteRepository
import com.app.contacts.data.api.PhoneAndEmailRepository
import com.app.contacts.data.source.model.SourceContact
import com.app.contacts.data.source.model.SourceEmail
import com.app.contacts.data.source.model.SourcePhoneNumber
import com.app.contacts.init.SingletonFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactSyncWorker(
    private val appContext: Context,
    private val params: WorkerParameters,
) : CoroutineWorker(
        appContext,
        params,
    ) {
    private val contactRepository: ContactRepository<SourceContact> =
        SingletonFactory.getContactRepository()
    private val phoneAndEmailRepository: PhoneAndEmailRepository<SourcePhoneNumber, SourceEmail> =
        SingletonFactory.getPhoneAndEmailRepository()
    private val contactWriteRepository: ContactWriteRepository<SourceContact, SourcePhoneNumber, SourceEmail> =
        SingletonFactory.getContactWriteRepository()

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.Default) {
            val allContact = contactRepository.getAllContact()
            val phoneNumbers = phoneAndEmailRepository.getAllPhoneNumbers()
            val emails = phoneAndEmailRepository.getAllEmails()

            var success = true

            allContact.onSuccess {
                contactWriteRepository.writeContacts(it)
            }.onFailure {
                success = false
                // todo analytics
            }

            phoneNumbers.onSuccess {
                contactWriteRepository.writePhoneNumbers(it)
            }.onFailure {
                success = false
                // todo analytics
            }

            emails.onSuccess {
                contactWriteRepository.writeEmailIds(it)
            }.onFailure {
                success = false
                // todo analytics
            }

            if (success) Result.success() else Result.retry()
        }
    }

    companion object {
        const val WORKER_CONTACT_NAME = "ContactSyncWorker"
    }
}
