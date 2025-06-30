package dev.onecoffeeplz.zamechalka.data.repository

import dev.onecoffeeplz.zamechalka.data.mapper.toNote
import dev.onecoffeeplz.zamechalka.data.mapper.toNoteEntity
import dev.onecoffeeplz.zamechalka.data.source.local.db.dao.NoteDao
import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun createNote(note: Note): Result<Unit> = runCatching {
        val id = noteDao.createNote(note.toNoteEntity())
        if (id == -1L) throw IllegalStateException("Create failed: note already exists")
    }

    override suspend fun updateNote(note: Note): Result<Unit> = runCatching {
        val updatedRow = noteDao.updateNote(note.toNoteEntity())
        if (updatedRow == 0) throw IllegalStateException("Update failed: note not found")
    }

    override suspend fun deleteNote(note: Note): Result<Unit> = runCatching {
        val deletedRow = noteDao.deleteNote(note.toNoteEntity())
        if (deletedRow == 0) throw IllegalStateException("Delete failed: note not found")
    }

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes().map { noteEntities ->
            noteEntities.map { it.toNote() }
        }
    }

    override fun getNoteById(noteId: Long): Flow<Note?> {
        return noteDao.getNoteById(noteId).map { it?.toNote() }
    }
}