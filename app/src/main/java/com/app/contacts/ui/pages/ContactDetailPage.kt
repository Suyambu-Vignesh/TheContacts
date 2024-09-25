package com.app.contacts.ui.pages

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.contacts.ui.theme.LocalDimens
import com.app.contacts.ui.viewmodel.ContactViewmodel

@Composable
fun ContactDetailPage(
    modifier: Modifier = Modifier,
    contactIndex: Int,
    viewModel: ContactViewmodel,
) {
    val contactListAsState = viewModel.contactPagingData.collectAsLazyPagingItems()
    // todo check itemId in range
    contactListAsState.get(contactIndex)?.let {
        Column(
            modifier
                .fillMaxWidth()
                .padding(LocalDimens.current.mediumPadding),
            verticalArrangement = spacedBy(LocalDimens.current.smallPadding, CenterVertically),
        ) {
            Text(text = it.displayName, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(20.dp))
            Text(text = it.email.toString(), style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(20.dp))
            Text(text = it.phoneNumbers.toString(), style = MaterialTheme.typography.titleMedium)
        }
    }
}

internal const val CONST_ARG_CONTACT_INDEX = "index"
internal const val CONST_CONTACT_DETAIL_PAGE = "contactDetail"
internal const val CONST_CONTACT_DETAIL_PAGE_URI = "contactDetail?$CONST_ARG_CONTACT_INDEX={$CONST_ARG_CONTACT_INDEX}"
