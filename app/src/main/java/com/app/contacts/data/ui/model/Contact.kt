package com.app.contacts.data.ui.model

data class Contact(
    internal val id: String,
    internal val displayName: String,
    internal val phoneNumbers: List<String>,
    internal val email: List<String>,
)
