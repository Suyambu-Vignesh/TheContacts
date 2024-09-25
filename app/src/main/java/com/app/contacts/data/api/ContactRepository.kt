package com.app.contacts.data.api

import kotlinx.coroutines.flow.Flow

/**
 * Repository for the Contact
 */
interface ContactRepository<DataType> {
    /**
     * Method to fetch the list of contact
     */
    suspend fun getAllContact(): Flow<DataType>
}
