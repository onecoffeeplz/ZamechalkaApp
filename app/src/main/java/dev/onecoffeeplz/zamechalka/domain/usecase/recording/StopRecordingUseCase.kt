package dev.onecoffeeplz.zamechalka.domain.usecase.recording

interface StopRecordingUseCase {
    suspend operator fun invoke(): Result<String>
}