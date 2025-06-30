package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.repository.AudioRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.StartRecordingUseCase

class StartRecordingUseCaseImpl(private val audioRepository: AudioRepository) :
    StartRecordingUseCase {
    override suspend fun invoke(): Result<Unit> {
        return audioRepository.startRecording()
    }
}