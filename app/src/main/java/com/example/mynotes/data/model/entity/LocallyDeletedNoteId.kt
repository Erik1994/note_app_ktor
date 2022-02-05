package com.example.mynotes.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynotes.data.local.DELETED_NOTE_TABLE_ID_NAME

@Entity(tableName = DELETED_NOTE_TABLE_ID_NAME)
data class LocallyDeletedNoteId(
    @PrimaryKey(autoGenerate = false)
    val deletedNoteId: String
)