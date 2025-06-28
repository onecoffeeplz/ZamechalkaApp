package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.domain.repository.NoteRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.GetNoteByIdUseCase
import kotlinx.coroutines.flow.Flow

class GetNoteByIdUseCaseImpl(private val repository: NoteRepository) : GetNoteByIdUseCase {
    override fun invoke(noteId: Long): Flow<Note?> {
        return repository.getNoteById(noteId)
    }
}