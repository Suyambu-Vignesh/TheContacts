package com.app.contacts.ui.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.contacts.ui.appbar.AppBar
import com.app.contacts.ui.appbar.AppBarState
import com.app.contacts.ui.navigation.AppNavigation

@Composable
fun MainActivityScreen() {
    val navState = rememberNavController()
    val appBarState = remember { mutableStateOf(AppBarState()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar(state = appBarState.value) },
    ) { paddingValues ->
        AppNavigation(modifier = Modifier.padding(paddingValues), navController = navState)
    }
}
