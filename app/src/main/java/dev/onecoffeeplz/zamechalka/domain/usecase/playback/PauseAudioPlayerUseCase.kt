package dev.onecoffeeplz.zamechalka.domain.usecase.playback

interface PauseAudioPlayerUseCase {
    operator fun invoke(): Result<Unit>
}