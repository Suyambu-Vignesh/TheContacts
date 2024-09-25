package com.app.contacts.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.contacts.data.ui.UiContactRepository
import com.app.contacts.data.ui.model.Contact
import kotlinx.coroutines.flow.Flow

class ContactViewmodel : ViewModel() {
    private val repository by lazy { UiContactRepository() }
    internal val contactPagingData: Flow<PagingData<Contact>> =
        repository.getAllContact().cachedIn(viewModelScope)
}
