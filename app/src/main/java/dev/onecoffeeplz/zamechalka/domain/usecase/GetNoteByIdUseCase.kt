package dev.onecoffeeplz.zamechalka.domain.usecase

import dev.onecoffeeplz.zamechalka.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface GetNoteByIdUseCase {
    operator fun invoke(noteId: Long): Flow<Note?>
}