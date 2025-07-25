package dev.onecoffeeplz.zamechalka.presentation.state

data class AudioPlayerState(
    val isPrepared: Boolean = false,
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val error: String? = null,
    val filePath: String,
)