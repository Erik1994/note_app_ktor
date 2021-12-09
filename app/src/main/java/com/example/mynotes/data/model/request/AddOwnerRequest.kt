package com.example.mynotes.data.model.request

import com.google.gson.annotations.SerializedName

data class AddOwnerRequest(
    @SerializedName("owner")
    val ownerEmail: String?,
    @SerializedName("noteId")
    val noteId: String?
)