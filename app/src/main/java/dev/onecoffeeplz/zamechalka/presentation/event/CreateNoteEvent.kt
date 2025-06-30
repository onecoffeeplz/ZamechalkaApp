package dev.onecoffeeplz.zamechalka.presentation.event

import dev.onecoffeeplz.zamechalka.domain.model.Note

sealed class CreateNoteEvent {
    data object StartRecording : CreateNoteEvent()
    data object StopRecording : CreateNoteEvent()
    data class SaveRecording(val filename: String) : CreateNoteEvent()
    data class SaveNote(val note: Note) : CreateNoteEvent()
}
