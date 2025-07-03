package dev.onecoffeeplz.zamechalka.presentation.event

sealed class NotesListEvent {
    data object LoadData : NotesListEvent()
}