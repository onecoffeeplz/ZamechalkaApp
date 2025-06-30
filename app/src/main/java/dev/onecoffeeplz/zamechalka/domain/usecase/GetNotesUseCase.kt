package dev.onecoffeeplz.zamechalka.domain.usecase

import dev.onecoffeeplz.zamechalka.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface GetNotesUseCase {
    operator fun invoke(): Flow<List<Note>>
}