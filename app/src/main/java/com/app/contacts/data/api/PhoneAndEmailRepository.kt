package com.app.contacts.data.api

internal interface PhoneAndEmailRepository<PhoneType, EmailType> {
    /**
     * Helper method to get all the phone numbers from App's Contact
     */
    fun getAllPhoneNumbers(): Result<List<PhoneType>>

    /**
     * Helper method to get all the email from App's Email
     */
    fun getAllEmails(): Result<List<EmailType>>
}
