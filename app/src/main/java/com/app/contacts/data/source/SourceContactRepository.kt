package com.app.contacts.data.source

import com.app.contacts.data.api.ContactRepository
import com.app.contacts.data.source.model.SourceContactWithDetails

class SourceContactRepository : ContactRepository<SourceContactWithDetails> {
    override suspend fun getAllContact(): Result<List<SourceContactWithDetails>> {
        //todo
    }
}