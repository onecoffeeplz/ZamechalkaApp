package dev.onecoffeeplz.zamechalka.domain.usecase.playback

interface GetAudioPlaybackPositionUseCase {
    operator fun invoke(): Long?
}