package com.example.mynotes.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynotes.data.local.NOTE_TABLE_NAME
import java.util.*

@Entity(tableName = NOTE_TABLE_NAME)
data class NoteEntity(
    val title: String,
    val content: String,
    val date: Long,
    val owners: List<String>,
    val color: String,
    var isSynced: Boolean = false,
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString()
)