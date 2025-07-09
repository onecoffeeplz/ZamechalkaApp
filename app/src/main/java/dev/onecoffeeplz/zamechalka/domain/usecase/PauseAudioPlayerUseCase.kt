package dev.onecoffeeplz.zamechalka.domain.usecase

interface PauseAudioPlayerUseCase {
    operator fun invoke(): Result<Unit>
}