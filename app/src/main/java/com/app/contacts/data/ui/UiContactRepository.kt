package com.app.contacts.data.ui

import com.app.contacts.data.api.ContactRepository
import com.app.contacts.data.ui.model.Contact

class UiContactRepository(

) : ContactRepository<Contact> {
    override suspend fun getAllContact(): Result<List<Contact>> {

    }
}