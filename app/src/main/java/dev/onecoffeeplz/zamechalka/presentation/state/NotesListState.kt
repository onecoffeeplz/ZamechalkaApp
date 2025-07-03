package dev.onecoffeeplz.zamechalka.presentation.state

import dev.onecoffeeplz.zamechalka.domain.model.Note

data class NotesListState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList(),
    val error: String? = null,
    val isEmpty: Boolean = false,
)
