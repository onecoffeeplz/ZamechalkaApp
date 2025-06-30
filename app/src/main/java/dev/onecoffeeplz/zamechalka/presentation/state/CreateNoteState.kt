package dev.onecoffeeplz.zamechalka.presentation.state

data class CreateNoteState(
    val isRecording: Boolean = false,
    val recordingDuration: Long = 0L,
    val recordingFilePath: String? = null,
    val error: String? = null,
    val recordWasSaved: Boolean = false,
)
