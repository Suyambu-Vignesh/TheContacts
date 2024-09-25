package com.app.contacts.data

import android.content.Context
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.app.contacts.data.worker.ContactSyncWorker
import com.app.contacts.ui.permission.Permission
import java.util.concurrent.TimeUnit

class ContactWorkManagerInitializer : Initializer<WorkRequest> {
    override fun create(context: Context): WorkRequest {
        val workManager = WorkManager.getInstance(context)
        val request =
            OneTimeWorkRequestBuilder<ContactSyncWorker>().apply {
                this.setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                    .setInitialDelay(0, TimeUnit.SECONDS)
                    .setConstraints(Constraints.NONE)
            }.build()

        if (Permission.isPermissionGranted(context, Permission.PERMISSION_READ_CONTACT)) {
            workManager.enqueueUniqueWork(
                ContactSyncWorker.WORKER_CONTACT_NAME,
                ExistingWorkPolicy.REPLACE,
                request,
            )
        }

        return request
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return arrayListOf(androidx.work.WorkManagerInitializer::class.java)
    }
}

private class WorkManagerInitializer : Initializer<WorkManager> {
    override fun create(context: Context): WorkManager {
        val configuration = Configuration.Builder().build()
        WorkManager.initialize(context, configuration)
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
