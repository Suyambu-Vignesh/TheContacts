package com.app.contacts.ui.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.contacts.ui.view.ContactCard
import com.app.contacts.ui.viewmodel.ContactViewmodel

@Composable
fun ContactListPage(
    modifier: Modifier = Modifier,
    viewModel: ContactViewmodel,
    navController: NavHostController,
) {
    val contactListAsState = viewModel.contactPagingData.collectAsLazyPagingItems()

    LazyColumn(modifier) {
        items(contactListAsState.itemCount) { index ->
            contactListAsState[index]?.let {
                ContactCard(contact = it) {
                    navController.navigate("$CONST_CONTACT_DETAIL_PAGE?$CONST_ARG_CONTACT_INDEX=$index")
                }
            }
        }
    }
}

internal const val CONST_CONTACT_LIST = "ContactList"
