package dev.onecoffeeplz.zamechalka.domain.usecase.impl.playback

import dev.onecoffeeplz.zamechalka.domain.repository.AudioPlayerRepository
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.GetAudioPlaybackPositionUseCase

class GetAudioPlaybackPositionUseCaseImpl(private val audioPlayerRepository: AudioPlayerRepository) :
    GetAudioPlaybackPositionUseCase {
    override fun invoke(): Long? {
        return audioPlayerRepository.currentPosition()
    }
}