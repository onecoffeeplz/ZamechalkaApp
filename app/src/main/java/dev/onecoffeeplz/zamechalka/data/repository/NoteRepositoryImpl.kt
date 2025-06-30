package dev.onecoffeeplz.zamechalka.data.repository

import dev.onecoffeeplz.zamechalka.data.mapper.toNote
import dev.onecoffeeplz.zamechalka.data.mapper.toNoteEntity
import dev.onecoffeeplz.zamechalka.data.source.local.db.dao.NoteDao
import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun createNote(note: Note) {
        noteDao.createNote(note.toNoteEntity())
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toNoteEntity())
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