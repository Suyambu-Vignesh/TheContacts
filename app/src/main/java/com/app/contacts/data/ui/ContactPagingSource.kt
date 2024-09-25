package com.app.contacts.data.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.contacts.data.source.db.ContactDao
import com.app.contacts.data.source.model.SourceContactWithDetails
import com.app.contacts.data.ui.model.Contact
import com.app.contacts.init.SingletonFactory

class ContactPagingSource(
    private val contactDao: ContactDao = SingletonFactory.getContactDao(),
) : PagingSource<Int, Contact>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Contact> {
        return when (val result = contactDao.getAllContactWithDetail().load(params)) {
            is LoadResult.Page -> {
                LoadResult.Page(
                    result.data.toContacts(),
                    result.prevKey,
                    result.nextKey,
                    result.itemsBefore,
                    result.itemsAfter,
                )
            }

            is LoadResult.Error -> {
                LoadResult.Error(
                    result.throwable,
                )
            }

            is LoadResult.Invalid -> {
                LoadResult.Invalid()
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Contact>): Int {
        return 0
    }
}

private fun List<SourceContactWithDetails>.toContacts(): List<Contact> {
    return this.map {
        val emails = it.emails.map { email -> email.email }
        val phoneNumbers = it.emails.map { email -> email.email }
        Contact(
            it.contact.contactId,
            it.contact.name,
            phoneNumbers,
            emails,
        )
    }
}
