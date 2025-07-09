package dev.onecoffeeplz.zamechalka.domain.usecase

interface ReleaseAudioPlayerUseCase {
    operator fun invoke(): Result<Unit>
}