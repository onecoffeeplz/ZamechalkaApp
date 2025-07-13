package dev.onecoffeeplz.zamechalka.presentation.event

sealed class AudioPlayerEvent {
    data class Prepared(val filePath: String) : AudioPlayerEvent()
    data object PlayClicked : AudioPlayerEvent()
    data class PositionUpdated(val position: Long) : AudioPlayerEvent()
    data object PauseClicked : AudioPlayerEvent()
    data object Completed : AudioPlayerEvent()
    data class PlaybackError(val message: String) : AudioPlayerEvent()
    data object StopAndRelease: AudioPlayerEvent()
}