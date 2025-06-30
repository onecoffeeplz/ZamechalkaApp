package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.domain.repository.NoteRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.DeleteNoteUseCase

class DeleteNoteUseCaseImpl(private val repository: NoteRepository) : DeleteNoteUseCase {
    override suspend fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}