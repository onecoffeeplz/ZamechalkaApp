package dev.onecoffeeplz.zamechalka.domain.usecase.playback

interface StartAudioPlayerUseCase {
    operator fun invoke(): Result<Unit>
}