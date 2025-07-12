package dev.onecoffeeplz.zamechalka.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.GetAudioPlaybackPositionUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.PauseAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.PrepareAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.ReleaseAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.StartAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.presentation.effect.AudioPlayerEffect
import dev.onecoffeeplz.zamechalka.presentation.effect.AudioPlayerEffect.*
import dev.onecoffeeplz.zamechalka.presentation.event.AudioPlayerEvent
import dev.onecoffeeplz.zamechalka.presentation.event.AudioPlayerEvent.*
import dev.onecoffeeplz.zamechalka.presentation.state.AudioPlayerState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class NoteDetailsViewModel(
    val getAudioPlaybackPositionUseCase: GetAudioPlaybackPositionUseCase,
    val pauseAudioPlayerUseCase: PauseAudioPlayerUseCase,
    val prepareAudioPlayerUseCase: PrepareAudioPlayerUseCase,
    val releaseAudioPlayerUseCase: ReleaseAudioPlayerUseCase,
    val startAudioPlayerUseCase: StartAudioPlayerUseCase,
    val filePath: String,
) : ViewModel() {
    private val _state = MutableStateFlow(AudioPlayerState(filePath = filePath))
    val state: StateFlow<AudioPlayerState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<AudioPlayerEffect>()
    val effects = _effects.asSharedFlow()

    private var timerJob: Job? = null

    fun onEvent(event: AudioPlayerEvent) {
        when (event) {
            is PlayClicked -> {
                if (!_state.value.isPlaying) {
                    if (!_state.value.isPrepared) {
                        viewModelScope.launch { _effects.emit(PreparePlayer(_state.value.filePath)) }
                    } else {
                        viewModelScope.launch { _effects.emit(PlayAudio) }
                        _state.update { it.copy(isPlaying = true) }
                        updateListenProgress()
                    }
                }
            }

            is PauseClicked -> {
                if (_state.value.isPlaying) {
                    _state.update { it.copy(isPlaying = false, error = null) }
                    viewModelScope.launch { _effects.emit(PauseAudio) }
                    cancelUpdateListenProgress()
                }
            }

            is PlaybackError -> {
                _state.update {
                    it.copy(
                        isPrepared = false,
                        isPlaying = false,
                        error = event.message
                    )
                }
                cancelUpdateListenProgress()
            }

            is Completed -> {
                _state.update { it.copy(isPlaying = false, currentPosition = 0L, error = null) }
                cancelUpdateListenProgress()
            }

            is Prepared -> {
                _state.update { it.copy(isPrepared = true, isPlaying = true) }
                viewModelScope.launch { _effects.emit(PlayAudio) }
                updateListenProgress()
            }

            is PositionUpdated -> {
                _state.update { it.copy(currentPosition = event.position) }
            }

            StopAndRelease -> {
                _state.update {
                    it.copy(
                        isPlaying = false,
                        isPrepared = false,
                        currentPosition = 0L,
                        error = null,
                    )
                }
                cancelUpdateListenProgress()
                viewModelScope.launch {
                    _effects.emit(ReleasePlayer)
                }
            }
        }
    }

    fun handleEffect(effect: AudioPlayerEffect) {
        when (effect) {
            is PreparePlayer -> {
                val result = prepareAudioPlayerUseCase(
                    filePath = effect.filePath,
                    onPrepared = {
                        onEvent(Prepared(effect.filePath))
                    },
                    onCompletion = {
                        onEvent(Completed)
                    },
                    onError = {
                        val errorMessage = it.message ?: "Unknown playback error"
                        onEvent(PlaybackError(errorMessage))
                    },
                )
                if (result.isFailure) {
                    val errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
                    onEvent(PlaybackError(errorMessage))
                }
            }

            is PlayAudio -> {
                val result = startAudioPlayerUseCase()
                if (result.isFailure) {
                    val errorMessage =
                        result.exceptionOrNull()?.message ?: "Failed to start playback"
                    onEvent(PlaybackError(errorMessage))
                }
            }

            PauseAudio -> {
                pauseAudioPlayerUseCase()
            }

            ReleasePlayer -> {
                pauseAudioPlayerUseCase()
                releaseAudioPlayerUseCase()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelUpdateListenProgress()
        releaseAudioPlayerUseCase()
        _state.update {
            it.copy(
                isPrepared = false,
                isPlaying = false,
                currentPosition = 0L,
            )
        }
    }

    private fun updateListenProgress() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (isActive) {
                val currentPosition = getAudioPlaybackPositionUseCase()
                if (currentPosition != null) {
                    onEvent(PositionUpdated(currentPosition))
                }
                delay(TIMER_UPDATE_TIME)
            }
        }
    }

    private fun cancelUpdateListenProgress() {
        timerJob?.cancel()
        timerJob = null
    }

    companion object {
        private const val TIMER_UPDATE_TIME = 300L
    }

}