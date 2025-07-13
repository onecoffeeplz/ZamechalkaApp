package dev.onecoffeeplz.zamechalka.domain.usecase.impl.recording

import dev.onecoffeeplz.zamechalka.domain.repository.AudioRecordRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.recording.DeleteRecordingUseCase

class DeleteRecordingUseCaseImpl(private val audioRecordRepository: AudioRecordRepository) :
    DeleteRecordingUseCase {
    override suspend fun invoke(path: String): Result<Unit> {
        return audioRecordRepository.deleteRecording(path)
    }
}