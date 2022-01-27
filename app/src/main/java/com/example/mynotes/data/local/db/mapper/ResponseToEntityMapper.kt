package com.example.mynotes.data.local.db.mapper

import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.NoteRequest
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


val NOTES_RESPONSE_TO_ENTITY_MAPPER =
    object : Mapper<MapperDataHolder<List<NoteResponse>>, List<NoteEntity>> {
        override fun map(source: MapperDataHolder<List<NoteResponse>>): List<NoteEntity> =
            source.data.map {
                NOTE_RESPONSE_TO_ENTITY_MAPPER.map(it)
            }
    }

val NOTE_REQUEST_TO_ENTITY_MAPPER = object : Mapper<NoteRequest, NoteEntity> {
    override fun map(source: NoteRequest): NoteEntity = NoteEntity(
        source.title,
        source.content,
        source.date,
        source.owners,
        source.color,
        id = source.id
    )
}