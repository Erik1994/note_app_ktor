package com.example.mynotes.data.model.response

data class NoteResponse(
    val title: String?,
    val content: String?,
    val date: Long?,
    val owners: List<String>?,
    val color: String?
)