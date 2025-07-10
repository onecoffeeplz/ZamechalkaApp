package dev.onecoffeeplz.zamechalka.domain.usecase.view

import dev.onecoffeeplz.zamechalka.domain.model.Note

interface CreateNoteUseCase {
    suspend operator fun invoke(note: Note): Result<Unit>
}