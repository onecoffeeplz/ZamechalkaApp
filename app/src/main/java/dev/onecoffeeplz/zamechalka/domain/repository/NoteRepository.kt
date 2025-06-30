package dev.onecoffeeplz.zamechalka.domain.repository

import dev.onecoffeeplz.zamechalka.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun createNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun getNotes(): Flow<List<Note>>
    fun getNoteById(noteId: Long): Flow<Note?>
}