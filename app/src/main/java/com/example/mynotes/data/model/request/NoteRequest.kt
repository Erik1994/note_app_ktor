package com.example.mynotes.data.model.request

import com.google.gson.annotations.SerializedName

data class NoteRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("date")
    val date: Long,
    @SerializedName("owners")
    val owners: List<String>,
    @SerializedName("color")
    val color: String,
    @SerializedName("id")
    val id: String
)