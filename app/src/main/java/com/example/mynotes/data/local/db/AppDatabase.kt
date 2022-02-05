package com.example.mynotes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mynotes.data.local.db.converter.Converters
import com.example.mynotes.data.local.db.dao.NoteDao
import com.example.mynotes.data.model.entity.LocallyDeletedNoteId
import com.example.mynotes.data.model.entity.NoteEntity

@Database(
    entities = [NoteEntity::class, LocallyDeletedNoteId::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}