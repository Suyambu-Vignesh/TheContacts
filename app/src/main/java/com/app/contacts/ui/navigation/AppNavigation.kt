package com.app.contacts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.contacts.ui.pages.CONST_CONTACT_DETAIL_PAGE
import com.app.contacts.ui.pages.CONST_CONTACT_LIST
import com.app.contacts.ui.pages.ContactDetailPage
import com.app.contacts.ui.pages.ContactListPage

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = CONST_CONTACT_LIST,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        this.composable(CONST_CONTACT_LIST) {
            ContactListPage()
        }

        this.composable(CONST_CONTACT_DETAIL_PAGE) {
            ContactDetailPage()
        }
    }
}
