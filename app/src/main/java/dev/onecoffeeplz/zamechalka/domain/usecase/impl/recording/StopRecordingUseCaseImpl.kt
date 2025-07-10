package dev.onecoffeeplz.zamechalka.domain.usecase.impl.recording

import dev.onecoffeeplz.zamechalka.domain.repository.AudioRecordRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.recording.StopRecordingUseCase

class StopRecordingUseCaseImpl(private val audioRecordRepository: AudioRecordRepository) :
    StopRecordingUseCase {
    override suspend fun invoke(): Result<String> {
        return audioRecordRepository.stopRecording()
    }
}