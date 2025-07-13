package dev.onecoffeeplz.zamechalka.domain.usecase.impl.playback

import dev.onecoffeeplz.zamechalka.domain.repository.AudioPlayerRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.ReleaseAudioPlayerUseCase

class ReleaseAudioPlayerUseCaseImpl(private val audioPlayerRepository: AudioPlayerRepository): ReleaseAudioPlayerUseCase {
    override fun invoke(): Result<Unit> {
        return audioPlayerRepository.release()
    }
}