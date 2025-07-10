package dev.onecoffeeplz.zamechalka.domain.usecase.impl.view

import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.domain.repository.NoteRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.view.CreateNoteUseCase

class CreateNoteUseCaseImpl(private val repository: NoteRepository) : CreateNoteUseCase {
    override suspend fun invoke(note: Note): Result<Unit> {
        return repository.createNote(note)
    }
}