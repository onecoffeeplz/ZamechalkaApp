package dev.onecoffeeplz.zamechalka.domain.usecase.impl.view

import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.domain.repository.NoteRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.view.GetNotesUseCase
import kotlinx.coroutines.flow.Flow

class GetNotesUseCaseImpl(private val repository: NoteRepository): GetNotesUseCase {
    override fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}