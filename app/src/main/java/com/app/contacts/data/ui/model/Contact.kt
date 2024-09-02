package com.app.contacts.data.ui.model

data class Contact(
    private val id: String,
    private val displayName: String,
    private val phoneNumbers: List<String>,
    private val email: List<String>
)