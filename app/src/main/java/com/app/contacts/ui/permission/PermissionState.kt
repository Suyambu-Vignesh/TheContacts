package com.app.contacts.ui.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember

class PermissionState {
    private val permissionMap by lazy {
        mutableStateMapOf<Permission, Boolean>(
            Permission.PERMISSION_READ_CONTACT to false,
        )
    }

    internal fun updatePermission(
        permission: Permission,
        value: Boolean,
    ) {
        permissionMap[permission] = value
    }

    @Composable
    fun getPermission(permission: Permission): State<Boolean> {
        return remember {
            derivedStateOf { permissionMap[permission] ?: false }
        }
    }
}
