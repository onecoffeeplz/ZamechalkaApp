package dev.onecoffeeplz.zamechalka.domain.usecase.impl

import dev.onecoffeeplz.zamechalka.domain.repository.AudioPlayerRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.GetAudioPlaybackPositionUseCase

class GetAudioPlaybackPositionUseCaseImpl(private val audioPlayerRepository: AudioPlayerRepository) :
    GetAudioPlaybackPositionUseCase {
    override fun invoke(): Long? {
        return audioPlayerRepository.currentPosition()
    }
}