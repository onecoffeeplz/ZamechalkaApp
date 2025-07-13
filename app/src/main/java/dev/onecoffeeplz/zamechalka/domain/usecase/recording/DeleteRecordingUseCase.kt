package dev.onecoffeeplz.zamechalka.domain.usecase.recording

interface DeleteRecordingUseCase {
    suspend operator fun invoke(path: String): Result<Unit>
}