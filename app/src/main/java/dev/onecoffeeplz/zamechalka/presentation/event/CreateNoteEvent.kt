package dev.onecoffeeplz.zamechalka.presentation.event

sealed class CreateNoteEvent {
    data object StartRecording : CreateNoteEvent()
    data object StopRecording : CreateNoteEvent()
}
