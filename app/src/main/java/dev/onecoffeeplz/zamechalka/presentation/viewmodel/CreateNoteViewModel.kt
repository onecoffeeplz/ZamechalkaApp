package dev.onecoffeeplz.zamechalka.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.onecoffeeplz.zamechalka.domain.usecase.StartRecordingUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.StopRecordingUseCase
import dev.onecoffeeplz.zamechalka.presentation.event.CreateNoteEvent
import dev.onecoffeeplz.zamechalka.presentation.state.CreateNoteState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateNoteViewModel(
    private val startRecordingUseCase: StartRecordingUseCase,
    private val stopRecordingUseCase: StopRecordingUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CreateNoteState())
    val state: StateFlow<CreateNoteState> = _state.asStateFlow()

    private var recordingStartTime: Long = 0L
    private var timerJob: Job? = null

    fun onEvent(event: CreateNoteEvent) {
        when (event) {
            is CreateNoteEvent.StartRecording -> startRecording()
            is CreateNoteEvent.StopRecording -> stopRecording()
            is CreateNoteEvent.SaveRecording -> saveRecording()
            is CreateNoteEvent.SaveNote -> saveNote()
        }
    }

    private fun startRecording() = viewModelScope.launch {
        val result = startRecordingUseCase()
        recordingStartTime = System.currentTimeMillis()
        updateRecordingTimeProgress()
        _state.update {
            if (result.isSuccess) {
                it.copy(isRecording = true, recordingFilePath=null, error = null)
            } else {
                it.copy(isRecording = false, recordingFilePath=null, error = result.exceptionOrNull()?.message)
            }
        }
    }

    private fun stopRecording() = viewModelScope.launch {
        val result = stopRecordingUseCase()
        stopRecordingTimeProgress()
        _state.update {
            if (result.isSuccess) it.copy(
                isRecording = false,
                recordingFilePath = result.getOrNull(),
                error = null
            )
            else it.copy(
                isRecording = false,
                recordingFilePath = result.getOrNull(),
                error = result.exceptionOrNull()?.message
            )
        }
    }

    private fun saveRecording() {}

    private fun saveNote() {}

    private fun updateRecordingTimeProgress() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(DURATION_UPDATE_TIME)
                val recordingDuration = System.currentTimeMillis() - recordingStartTime
                _state.update { it.copy(recordingDuration = recordingDuration) }
            }
        }
    }

    private fun stopRecordingTimeProgress() {
        timerJob?.cancel()
        timerJob = null
    }

    companion object {
        private const val DURATION_UPDATE_TIME = 1000L
    }
}