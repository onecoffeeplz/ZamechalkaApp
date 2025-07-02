package dev.onecoffeeplz.zamechalka.domain.usecase

interface DeleteRecordingUseCase {
    suspend operator fun invoke(path: String): Result<Unit>
}