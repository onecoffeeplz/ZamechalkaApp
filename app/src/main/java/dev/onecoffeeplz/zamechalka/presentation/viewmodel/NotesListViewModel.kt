package dev.onecoffeeplz.zamechalka.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.onecoffeeplz.zamechalka.domain.usecase.GetNotesUseCase
import dev.onecoffeeplz.zamechalka.presentation.event.NotesListEvent
import dev.onecoffeeplz.zamechalka.presentation.state.NotesListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class NotesListViewModel(
    private val getNotesUseCase: GetNotesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(NotesListState())
    val state: StateFlow<NotesListState> = _state.asStateFlow()

    fun onEvent(event: NotesListEvent) {
        when (event) {
            is NotesListEvent.LoadData -> getNotes()
        }
    }

    private fun getNotes() = viewModelScope.launch {
        getNotesUseCase()
            .onStart {
                Timber.d("Show loading state")
                _state.update {
                    it.copy(
                        isLoading = true,
                        notes = emptyList(),
                        error = null,
                        isEmpty = false,
                    )
                }
            }
            .catch { error ->
                Timber.e(error, "Error loading notes: ${error.message}")
                _state.update {
                    it.copy(
                        isLoading = false,
                        notes = emptyList(),
                        error = error.message,
                        isEmpty = true,
                    )
                }
            }
            .collect { notes ->
                when {
                    notes.isEmpty() -> {
                        Timber.d("Loaded empty notes list")
                        _state.update {
                            it.copy(
                                isLoading = false,
                                notes = emptyList(),
                                error = null,
                                isEmpty = true,
                            )
                        }
                    }

                    else -> {
                        Timber.d("Successfully loaded %d notes", notes.size)
                        _state.update {
                            it.copy(
                                isLoading = false,
                                notes = notes,
                                error = null,
                                isEmpty = false,
                            )
                        }
                    }
                }
            }
    }
}