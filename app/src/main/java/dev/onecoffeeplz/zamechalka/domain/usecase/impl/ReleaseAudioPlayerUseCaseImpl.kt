package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.repository.AudioPlayerRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.ReleaseAudioPlayerUseCase

class ReleaseAudioPlayerUseCaseImpl(private val audioPlayerRepository: AudioPlayerRepository): ReleaseAudioPlayerUseCase {
    override fun invoke(): Result<Unit> {
        return audioPlayerRepository.release()
    }
}