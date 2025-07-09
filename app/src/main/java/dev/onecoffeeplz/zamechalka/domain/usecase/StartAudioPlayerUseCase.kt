package dev.onecoffeeplz.zamechalka.domain.usecase

interface StartAudioPlayerUseCase {
    operator fun invoke(): Result<Unit>
}