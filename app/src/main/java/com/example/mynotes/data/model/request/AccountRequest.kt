package com.example.mynotes.data.model.request

import com.example.mynotes.ui.extensions.emptyString
import com.google.gson.annotations.SerializedName

data class AccountRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @Transient
    val repeatedPassword: String = emptyString()
)