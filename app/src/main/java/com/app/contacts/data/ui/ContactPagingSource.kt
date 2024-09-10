package com.app.contacts.data.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.contacts.data.source.db.ContactDao
import com.app.contacts.data.source.model.SourceContactWithDetails

class ContactPagingSource(
    private val contactDao: ContactDao,
) : PagingSource<Int, SourceContactWithDetails>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SourceContactWithDetails> {
        return contactDao.getAllContactWithDetail().load(params)
    }

    override fun getRefreshKey(state: PagingState<Int, SourceContactWithDetails>): Int {
        return 0
    }
}
