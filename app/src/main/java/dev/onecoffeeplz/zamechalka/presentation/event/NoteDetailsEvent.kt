package dev.onecoffeeplz.zamechalka.presentation.event

sealed class NoteDetailsEvent {
    data object StartPlaying: NoteDetailsEvent()
    data object PausePlaying: NoteDetailsEvent()
    data object StopPlaying: NoteDetailsEvent()
}