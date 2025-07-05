package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.repository.AudioRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.StartRecordingUseCase
import kotlinx.coroutines.CoroutineScope

class StartRecordingUseCaseImpl(private val audioRepository: AudioRepository) :
    StartRecordingUseCase {
    override suspend fun invoke(scope: CoroutineScope): Result<Unit> {
        return audioRepository.startRecording(scope)
    }
}