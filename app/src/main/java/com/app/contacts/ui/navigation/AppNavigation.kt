package com.app.contacts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.contacts.ui.pages.CONST_ARG_CONTACT_INDEX
import com.app.contacts.ui.pages.CONST_CONTACT_DETAIL_PAGE_URI
import com.app.contacts.ui.pages.CONST_CONTACT_LIST
import com.app.contacts.ui.pages.ContactDetailPage
import com.app.contacts.ui.pages.ContactListPage
import com.app.contacts.ui.viewmodel.ContactViewmodel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = CONST_CONTACT_LIST,
) {
    val viewModel: ContactViewmodel = viewModel()
    NavHost(navController = navController, startDestination = startDestination) {
        this.composable(CONST_CONTACT_LIST) {
            ContactListPage(modifier = modifier, viewModel = viewModel, navController = navController)
        }

        this.composable(
            route = CONST_CONTACT_DETAIL_PAGE_URI,
            arguments =
                listOf(
                    navArgument(CONST_ARG_CONTACT_INDEX) {
                        type = NavType.IntType
                        defaultValue = 0
                    },
                ),
        ) {
            val contactIndex = it.arguments?.getInt(CONST_ARG_CONTACT_INDEX) ?: 0
            ContactDetailPage(modifier = modifier, viewModel = viewModel, contactIndex = contactIndex)
        }
    }
}
