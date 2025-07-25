package dev.onecoffeeplz.zamechalka.domain.usecase.impl.view

import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.domain.repository.NoteRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.view.DeleteNoteUseCase

class DeleteNoteUseCaseImpl(private val repository: NoteRepository) : DeleteNoteUseCase {
    override suspend fun invoke(note: Note): Result<Unit> {
        return repository.deleteNote(note)
    }
}