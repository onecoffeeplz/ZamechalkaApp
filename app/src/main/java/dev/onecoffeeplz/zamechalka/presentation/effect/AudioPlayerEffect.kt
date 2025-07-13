package dev.onecoffeeplz.zamechalka.presentation.effect

sealed class AudioPlayerEffect {
    data class PreparePlayer(val filePath: String) : AudioPlayerEffect()
    data object PlayAudio : AudioPlayerEffect()
    data object PauseAudio : AudioPlayerEffect()
    data object ReleasePlayer : AudioPlayerEffect()
}