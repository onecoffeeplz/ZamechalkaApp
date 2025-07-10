package dev.onecoffeeplz.zamechalka.domain.usecase.playback

interface ReleaseAudioPlayerUseCase {
    operator fun invoke(): Result<Unit>
}