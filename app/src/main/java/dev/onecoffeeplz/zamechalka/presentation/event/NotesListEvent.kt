package dev.onecoffeeplz.zamechalka.presentation.event

import dev.onecoffeeplz.zamechalka.domain.model.Note

sealed class NotesListEvent {
    data object LoadData : NotesListEvent()
    data class DeleteNote(val note: Note): NotesListEvent()
}