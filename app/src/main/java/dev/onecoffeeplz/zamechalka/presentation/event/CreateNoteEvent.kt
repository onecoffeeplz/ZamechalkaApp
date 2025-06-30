package dev.onecoffeeplz.zamechalka.presentation.event

sealed class CreateNoteEvent {
    data object StartRecording : CreateNoteEvent()
    data object StopRecording : CreateNoteEvent()
    data class SaveRecording(
        val name: String,
        val path: String,
        val duration: Long,
    ) :
        CreateNoteEvent()
}
