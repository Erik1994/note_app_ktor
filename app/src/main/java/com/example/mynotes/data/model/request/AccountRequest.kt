package com.example.mynotes.data.model.request

import com.google.gson.annotations.SerializedName

data class AccountRequest(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?
)