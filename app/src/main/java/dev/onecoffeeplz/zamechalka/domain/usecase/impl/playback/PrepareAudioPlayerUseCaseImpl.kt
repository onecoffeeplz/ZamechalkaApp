package dev.onecoffeeplz.zamechalka.domain.usecase.impl.playback

import dev.onecoffeeplz.zamechalka.domain.repository.AudioPlayerRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.PrepareAudioPlayerUseCase

class PrepareAudioPlayerUseCaseImpl(private val audioPlayerRepository: AudioPlayerRepository): PrepareAudioPlayerUseCase {
    override fun invoke(
        filePath: String,
        onPrepared: () -> Unit,
        onCompletion: () -> Unit,
        onError: (Throwable) -> Unit
    ): Result<Unit> {
        return audioPlayerRepository.preparePlayer(filePath, onPrepared, onCompletion, onError)
    }
}