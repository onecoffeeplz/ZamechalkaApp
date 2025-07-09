package dev.onecoffeeplz.zamechalka.domain.usecase

interface GetAudioPlaybackPositionUseCase {
    operator fun invoke(): Long?
}