package dev.onecoffeeplz.zamechalka.domain.usecase

interface StartRecordingUseCase {
    suspend operator fun invoke(): Result<Unit>
}