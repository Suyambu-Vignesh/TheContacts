package com.app.contacts.ui.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun PermissionScreen(
    permissions: List<Permission>,
    permissionState: PermissionState,
) {
    var hasAllPermission = true

    for (permission in permissions) {
        hasAllPermission = hasAllPermission && Permission.isPermissionGranted(LocalContext.current, permission)

        if (hasAllPermission == false) {
            break
        }
    }

    if (hasAllPermission) {
        for (permission in permissions) {
            permissionState.updatePermission(permission, true)
        }
    }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
                it.forEach { (permissionKey, value) ->
                    Permission.fromValues(permissionKey)?.let { permission ->
                        permissionState.updatePermission(permission, value)
                    }
                }
            },
        )

    SideEffect {
        launcher.launch(permissions.map { it.value }.toTypedArray())
    }
}
