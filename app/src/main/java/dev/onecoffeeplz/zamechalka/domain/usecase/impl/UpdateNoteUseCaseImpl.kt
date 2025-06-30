package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.domain.repository.NoteRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.UpdateNoteUseCase

class UpdateNoteUseCaseImpl(private val repository: NoteRepository) : UpdateNoteUseCase {
    override suspend fun invoke(note: Note): Result<Unit> {
        return repository.updateNote(note)
    }
}