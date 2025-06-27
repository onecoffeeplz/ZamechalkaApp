package dev.onecoffeeplz.zamechalka.presentation.state

data class CreateNoteState(
    val isRecording: Boolean = false,
    val recordingDuration: Long = 0L,
    val error: String? = null,
)
