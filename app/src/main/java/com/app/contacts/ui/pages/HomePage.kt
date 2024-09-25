package com.app.contacts.ui.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.contacts.ui.appbar.AppBar
import com.app.contacts.ui.appbar.AppBarState
import com.app.contacts.ui.navigation.AppNavigation
import com.app.contacts.ui.permission.Permission
import com.app.contacts.ui.permission.PermissionScreen
import com.app.contacts.ui.permission.PermissionState

@Composable
fun HomePage() {
    val appBarState = remember { mutableStateOf(AppBarState()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar(state = appBarState.value) },
    ) { paddingValues ->
        val permissionState =
            remember {
                PermissionState()
            }
        val contactPermissionState by permissionState.getPermission(Permission.PERMISSION_READ_CONTACT)

        if (contactPermissionState) {
            val navState = rememberNavController()
            AppNavigation(modifier = Modifier.padding(paddingValues), navController = navState)
        } else {
            PermissionScreen(
                permissions = listOf(Permission.PERMISSION_READ_CONTACT),
                permissionState = permissionState,
            )
        }
    }
}
