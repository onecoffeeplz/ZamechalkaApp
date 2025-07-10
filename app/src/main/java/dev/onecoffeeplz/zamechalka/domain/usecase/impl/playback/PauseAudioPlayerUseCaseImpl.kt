package dev.onecoffeeplz.zamechalka.domain.usecase.impl.playback

import dev.onecoffeeplz.zamechalka.domain.repository.AudioPlayerRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.PauseAudioPlayerUseCase

class PauseAudioPlayerUseCaseImpl(private val audioPlayerRepository: AudioPlayerRepository): PauseAudioPlayerUseCase {
    override fun invoke(): Result<Unit> {
        return audioPlayerRepository.pausePlayer()
    }
}