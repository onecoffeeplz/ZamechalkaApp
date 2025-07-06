package dev.onecoffeeplz.zamechalka.presentation.state

data class NoteDetailsState(
    val isPlaying: Boolean = false,
    val error: String? = null,
)