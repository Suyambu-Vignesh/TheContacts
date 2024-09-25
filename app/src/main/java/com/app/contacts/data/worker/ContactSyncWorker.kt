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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class ContactSyncWorker(
    private val appContext: Context,
    private val params: WorkerParameters,
) : CoroutineWorker(
        appContext,
        params,
    ) {
    private val contactRepository: ContactRepository<kotlin.Result<List<SourceContact>>> =
        SingletonFactory.getContactRepository()
    private val phoneAndEmailRepository: PhoneAndEmailRepository<SourcePhoneNumber, SourceEmail> =
        SingletonFactory.getPhoneAndEmailRepository()
    private val contactWriteRepository: ContactWriteRepository<SourceContact, SourcePhoneNumber, SourceEmail> =
        SingletonFactory.getContactWriteRepository()

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val allContact = contactRepository.getAllContact().catch { }.first()
            val phoneNumbers = phoneAndEmailRepository.getAllPhoneNumbers()
            val emails = phoneAndEmailRepository.getAllEmails()

            var success = false

            allContact.getOrNull()?.let { contacts ->
                contactWriteRepository.writeContacts(contacts)
                emails.getOrNull()?.let {
                    contactWriteRepository.writeEmailIds(it)
                }
                phoneNumbers.getOrNull()?.let {
                    contactWriteRepository.writePhoneNumbers(it)
                }

                success = true
            }

            if (success) Result.success() else Result.retry()
        }
    }

    companion object {
        const val WORKER_CONTACT_NAME = "ContactSyncWorker"
    }
}
