package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.domain.repository.NoteRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.CreateNoteUseCase

class CreateNoteUseCaseImpl(private val repository: NoteRepository) : CreateNoteUseCase {
    override suspend fun invoke(note: Note) {
        repository.createNote(note)
    }
}