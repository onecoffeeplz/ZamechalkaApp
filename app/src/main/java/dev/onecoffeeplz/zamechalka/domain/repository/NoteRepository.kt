package dev.onecoffeeplz.zamechalka.domain.repository

import dev.onecoffeeplz.zamechalka.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun createNote(note: Note): Result<Unit>
    suspend fun updateNote(note: Note): Result<Unit>
    suspend fun deleteNote(note: Note): Result<Unit>
    fun getNotes(): Flow<List<Note>>
    fun getNoteById(noteId: Long): Flow<Note?>
}