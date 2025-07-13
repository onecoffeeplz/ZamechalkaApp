package dev.onecoffeeplz.zamechalka.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import dev.onecoffeeplz.zamechalka.presentation.ui.CreateNoteScreenUi
import dev.onecoffeeplz.zamechalka.presentation.ui.NoteDetailsScreenUi
import dev.onecoffeeplz.zamechalka.presentation.ui.NotesListScreenUi
import dev.onecoffeeplz.zamechalka.presentation.ui.SettingsScreenUi
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationRoot(
    topLevelBackStack: NavBackStack,
    modifier: Modifier,
) {
    NavDisplay(
        modifier = modifier,
        backStack = topLevelBackStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator(),
        ),
        entryProvider = { key ->
            when (key) {
                is TopLevelRoute.NotesListScreen -> {
                    NavEntry(
                        key = key,
                    ) {
                        NotesListScreenUi(
                            currentRoute = key,
                            onNoteClick = { note ->
                                topLevelBackStack.add(TopLevelRoute.NoteDetailsScreen(note))
                            },
                        )
                    }
                }

                is TopLevelRoute.NoteDetailsScreen -> {
                    NavEntry(
                        key = key,
                    ) {
                        NoteDetailsScreenUi(
                            note = key.note,
                            viewModel = koinViewModel { parametersOf(key.note) },
                        )
                    }
                }

                is TopLevelRoute.CreateNoteScreen -> {
                    NavEntry(
                        key = key,
                    ) {
                        CreateNoteScreenUi()
                    }

                }

                is TopLevelRoute.SettingsScreen -> {
                    NavEntry(
                        key = key,
                    ) {
                        SettingsScreenUi()
                    }
                }

                else -> throw RuntimeException("Invalid NavKey!")
            } as NavEntry<NavKey>
        }
    )
}