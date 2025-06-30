package dev.onecoffeeplz.zamechalka.domain.usecase

interface StopRecordingUseCase {
    suspend operator fun invoke(): Result<String>
}