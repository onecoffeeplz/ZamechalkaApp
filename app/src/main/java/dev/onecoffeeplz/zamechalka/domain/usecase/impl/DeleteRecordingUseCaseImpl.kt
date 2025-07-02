package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.repository.AudioRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.DeleteRecordingUseCase

class DeleteRecordingUseCaseImpl(private val audioRepository: AudioRepository) :
    DeleteRecordingUseCase {
    override suspend fun invoke(path: String): Result<Unit> {
        return audioRepository.deleteRecording(path)
    }
}