package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.repository.AudioRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.StopRecordingUseCase

class StopRecordingUseCaseImpl(private val audioRepository: AudioRepository) :
    StopRecordingUseCase {
    override suspend fun invoke(): Result<String> {
        return audioRepository.stopRecording()
    }
}