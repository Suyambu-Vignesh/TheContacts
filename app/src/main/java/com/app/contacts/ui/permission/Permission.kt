package com.app.contacts.ui.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

enum class Permission(val value: String) {
    PERMISSION_READ_CONTACT(Manifest.permission.READ_CONTACTS),
    ;

    companion object {
        fun fromValues(permission: String) = entries.toTypedArray().find { it.value == permission }

        fun isPermissionGranted(
            context: Context,
            permission: Permission,
        ): Boolean {
            return ContextCompat.checkSelfPermission(context, permission.value) == PackageManager.PERMISSION_GRANTED
        }
    }
}
