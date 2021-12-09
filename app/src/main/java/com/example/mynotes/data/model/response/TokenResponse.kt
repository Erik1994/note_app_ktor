package com.example.mynotes.data.model.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("successful")
    val successful: Boolean?,
    @SerializedName("message")
    val message: String?
)