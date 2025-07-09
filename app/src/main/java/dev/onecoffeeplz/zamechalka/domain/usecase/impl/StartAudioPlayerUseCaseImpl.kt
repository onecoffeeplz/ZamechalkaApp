package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.repository.AudioPlayerRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.StartAudioPlayerUseCase

class StartAudioPlayerUseCaseImpl(private val audioPlayerRepository: AudioPlayerRepository) :
    StartAudioPlayerUseCase {
    override fun invoke(): Result<Unit> {
        return audioPlayerRepository.startPlayer()
    }
}