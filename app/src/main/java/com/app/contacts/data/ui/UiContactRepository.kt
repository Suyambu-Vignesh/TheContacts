package com.app.contacts.data.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.contacts.data.ui.model.Contact
import kotlinx.coroutines.flow.Flow

class UiContactRepository {
    fun getAllContact(): Flow<PagingData<Contact>> {
        return Pager(
            PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = { ContactPagingSource() },
        ).flow
    }
}
