package com.example.mynotes.data.model.request

import com.google.gson.annotations.SerializedName

data class DeleteNoteRequest(
    @SerializedName("id")
    val noteId: String?
)