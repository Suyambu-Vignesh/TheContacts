package com.app.contacts.init

import android.content.Context
import androidx.startup.Initializer
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.app.contacts.data.worker.ContactSyncWorker
import java.util.concurrent.TimeUnit

class WorkManagerInitialization : Initializer<WorkRequest> {
    override fun create(context: Context): WorkRequest {
        val workManager = WorkManager.getInstance(context)

        val oneTimeRequest = OneTimeWorkRequestBuilder<ContactSyncWorker>().build()

        val request =
            PeriodicWorkRequestBuilder<ContactSyncWorker>(
                6,
                TimeUnit.HOURS,
                5,
                TimeUnit.HOURS,
            ).build()

        workManager.enqueueUniquePeriodicWork(
            ContactSyncWorker.WORKER_CONTACT_NAME,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            request,
        )

        workManager.enqueueUniqueWork(
            ContactSyncWorker.WORKER_CONTACT_NAME,
            ExistingWorkPolicy.REPLACE,
            oneTimeRequest,
        )

        return oneTimeRequest
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return arrayListOf()
    }
}
