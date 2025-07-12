package dev.onecoffeeplz.zamechalka.presentation.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.presentation.event.NotesListEvent
import dev.onecoffeeplz.zamechalka.navigation.TopLevelRoute
import dev.onecoffeeplz.zamechalka.presentation.ui.components.EmptyView
import dev.onecoffeeplz.zamechalka.presentation.ui.components.ErrorView
import dev.onecoffeeplz.zamechalka.presentation.ui.components.LoadingProgressBar
import dev.onecoffeeplz.zamechalka.presentation.ui.components.NotesListView
import dev.onecoffeeplz.zamechalka.presentation.viewmodel.NotesListViewModel
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NotesListScreenUi(
    currentRoute: Any,
    viewModel: NotesListViewModel = koinViewModel(),
    onNoteClick: (Note) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(currentRoute) {
        if (currentRoute == TopLevelRoute.NotesListScreen) {
            viewModel.onEvent(NotesListEvent.LoadData)
        }
    }

    when {
        state.isLoading -> {
            LoadingProgressBar()
        }

        state.isEmpty || state.notes.isEmpty() -> {
            EmptyView()
        }

        state.error != null -> state.error?.let {
            ErrorView(message = it)
        }

        else -> {
            Timber.d("NotesListScreen get notes: ${state.notes}")
            NotesListView(
                notes = state.notes,
                onNoteClick = onNoteClick,
                onSwipeToDelete = { viewModel.onEvent(NotesListEvent.DeleteNote(it)) })
        }
    }
}