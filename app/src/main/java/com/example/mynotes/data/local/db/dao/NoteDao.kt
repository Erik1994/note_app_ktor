package com.example.mynotes.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mynotes.data.local.NOTE_TABLE_NAME
import com.example.mynotes.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: List<NoteEntity>)

    @Query("DELETE FROM $NOTE_TABLE_NAME WHERE id = :noteId")
    suspend fun deleteNoteByID(noteId: String)

    @Query("DELETE FROM $NOTE_TABLE_NAME WHERE isSynced = 1")
    suspend fun deleteAllSyncedNotes()

    @Query("SELECT * FROM $NOTE_TABLE_NAME WHERE id = :noteId")
    fun observeNoteById(noteId: String): Flow<NoteEntity>

    @Query("SELECT * FROM $NOTE_TABLE_NAME WHERE id = :noteId")
    suspend fun getNoteById(noteId: String): NoteEntity?

    @Query("SELECT * FROM $NOTE_TABLE_NAME ORDER BY date DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM $NOTE_TABLE_NAME WHERE isSynced = 0")
    suspend fun getAllUnSyncedNotes(): List<NoteEntity>

}