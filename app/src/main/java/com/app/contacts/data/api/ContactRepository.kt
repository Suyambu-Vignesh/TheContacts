package com.app.contacts.data.api

/**
 * Repository for the Contact
 */
interface ContactRepository<DataType> {

    /**
     * Method to fetch the list of contact
     */
    suspend fun getAllContact(): Result<List<DataType>>
}