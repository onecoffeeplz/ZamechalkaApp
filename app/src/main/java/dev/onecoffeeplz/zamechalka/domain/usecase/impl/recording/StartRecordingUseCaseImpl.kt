package dev.onecoffeeplz.zamechalka.domain.usecase.impl.recording

import dev.onecoffeeplz.zamechalka.domain.repository.AudioRecordRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.recording.StartRecordingUseCase
import kotlinx.coroutines.CoroutineScope

class StartRecordingUseCaseImpl(private val audioRecordRepository: AudioRecordRepository) :
    StartRecordingUseCase {
    override suspend fun invoke(scope: CoroutineScope): Result<Unit> {
        return audioRecordRepository.startRecording(scope)
    }
}