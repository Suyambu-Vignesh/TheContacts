package com.app.contacts.data.api

interface ContactWriteRepository<ContactType, PhoneType, EmailType> {
    /**
     * Helper Method to write contacts to Store.
     */
    fun writeContacts(contacts: List<ContactType>)

    /**
     * Helper Method to write Phone numbers to Store.
     */
    fun writePhoneNumbers(phoneNumbers: List<PhoneType>)

    /**
     * Helper Method to write Email numbers to Store.
     */
    fun writeEmailIds(emailIds: List<EmailType>)
}
