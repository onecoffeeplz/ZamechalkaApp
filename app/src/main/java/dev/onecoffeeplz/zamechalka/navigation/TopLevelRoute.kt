package dev.onecoffeeplz.zamechalka.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import dev.onecoffeeplz.zamechalka.R
import dev.onecoffeeplz.zamechalka.domain.model.Note
import kotlinx.serialization.Serializable

internal sealed class TopLevelRoute : NavKey {
    abstract val icon: ImageVector?

    @get:StringRes
    abstract val label: Int

    @Serializable
    data object NotesListScreen : TopLevelRoute() {
        override val icon = Icons.Default.Menu
        override val label = R.string.notes_list
    }

    data class NoteDetailsScreen(val note: Note) : TopLevelRoute() {
        override val icon = null
        override val label = R.string.note_details
    }

    @Serializable
    data object CreateNoteScreen : TopLevelRoute() {
        override val icon = Icons.Default.Create
        override val label = R.string.create
    }

    @Serializable
    data object SettingsScreen : TopLevelRoute() {
        override val icon = Icons.Default.Settings
        override val label = R.string.settings
    }
}

internal val TOP_LEVEL_ROUTES: List<TopLevelRoute> = listOf(
    TopLevelRoute.NotesListScreen,
    TopLevelRoute.CreateNoteScreen,
    TopLevelRoute.SettingsScreen
)