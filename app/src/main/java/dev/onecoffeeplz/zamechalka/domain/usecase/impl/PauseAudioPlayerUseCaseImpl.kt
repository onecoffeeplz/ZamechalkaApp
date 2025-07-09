package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.repository.AudioPlayerRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.PauseAudioPlayerUseCase

class PauseAudioPlayerUseCaseImpl(private val audioPlayerRepository: AudioPlayerRepository): PauseAudioPlayerUseCase {
    override fun invoke(): Result<Unit> {
        return audioPlayerRepository.pausePlayer()
    }
}