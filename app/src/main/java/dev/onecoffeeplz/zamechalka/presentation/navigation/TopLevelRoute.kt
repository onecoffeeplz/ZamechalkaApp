package dev.onecoffeeplz.zamechalka.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import dev.onecoffeeplz.zamechalka.domain.model.Note

internal sealed class TopLevelRoute(val icon: ImageVector) {
    data object NotesList : TopLevelRoute(Icons.Default.Menu)
    data class NoteDetails(val note: Note)
    data object CreateNote : TopLevelRoute(Icons.Default.Create)
    data object Settings : TopLevelRoute(Icons.Default.Settings)
}

internal val TOP_LEVEL_ROUTES: List<TopLevelRoute> = listOf(
    TopLevelRoute.NotesList,
    TopLevelRoute.CreateNote,
    TopLevelRoute.Settings
)