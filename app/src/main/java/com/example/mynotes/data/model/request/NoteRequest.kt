package com.example.mynotes.data.model.request

data class NoteRequest(
    val title: String,
    val content: String,
    val date: Long,
    val owners: List<String>,
    val color: String
)