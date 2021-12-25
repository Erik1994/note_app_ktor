package com.example.mynotes.data.local.db.mapper

import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.response.NoteResponse
import com.example.mynotes.ui.extensions.emptyString

val NOTE_RESPONSE_TO_ENTITY_MAPPER = object : Mapper<NoteResponse, NoteEntity> {
    override fun map(source: NoteResponse): NoteEntity = NoteEntity(
        source.title ?: emptyString(),
        source.content ?: emptyString(),
        source.date ?: 0L,
        source.owners ?: emptyList(),
        source.color ?: emptyString()
    )
}


val NOTES_RESPONSE_TO_ENTITY_MAPPER = object : Mapper<List<NoteResponse>, List<NoteEntity>> {
    override fun map(source: List<NoteResponse>) = source.map {
        NOTE_RESPONSE_TO_ENTITY_MAPPER.map(it)
    }
}