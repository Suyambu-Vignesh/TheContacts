package com.app.contacts.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.contacts.data.ui.model.Contact
import com.app.contacts.ui.theme.LocalDimens

@Composable
fun ContactCard(
    modifier: Modifier = Modifier,
    contact: Contact,
    click: () -> Unit,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(LocalDimens.current.mediumPadding)
                .clickable { click.invoke() },
        horizontalArrangement = Arrangement.spacedBy(LocalDimens.current.smallPadding),
    ) {
        Text(text = contact.displayName[0].toString())
        Text(text = contact.displayName)
    }
}
