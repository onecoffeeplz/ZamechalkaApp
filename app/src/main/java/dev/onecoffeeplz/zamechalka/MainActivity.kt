package dev.onecoffeeplz.zamechalka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dev.onecoffeeplz.zamechalka.presentation.navigation.TOP_LEVEL_ROUTES
import dev.onecoffeeplz.zamechalka.presentation.navigation.TopLevelBackStack
import dev.onecoffeeplz.zamechalka.presentation.navigation.TopLevelRoute
import dev.onecoffeeplz.zamechalka.presentation.ui.CreateNoteScreen
import dev.onecoffeeplz.zamechalka.presentation.ui.NoteDetailsScreen
import dev.onecoffeeplz.zamechalka.presentation.ui.NotesListScreen
import dev.onecoffeeplz.zamechalka.presentation.ui.SettingsScreen
import dev.onecoffeeplz.zamechalka.presentation.ui.components.ZamechalkaTopBar
import dev.onecoffeeplz.zamechalka.presentation.ui.theme.ZamechalkaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZamechalkaTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val topLevelBackStack = remember { TopLevelBackStack<Any>(TopLevelRoute.NotesList) }

    Scaffold(
        topBar = { ZamechalkaTopBar() },
        bottomBar = {
            NavigationBar {
                TOP_LEVEL_ROUTES.forEach { topLevelRoute ->

                    val isSelected = topLevelRoute == topLevelBackStack.topLevelKey
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            topLevelBackStack.addTopLevel(topLevelRoute)
                        },
                        icon = {
                            Icon(
                                imageVector = topLevelRoute.icon,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }) { innerPadding ->
        NavDisplay(
            backStack = topLevelBackStack.backStack,
            onBack = { topLevelBackStack.removeLast() },
            entryProvider = entryProvider {
                entry<TopLevelRoute.NotesList> {
                    Column(
                        Modifier
                            .padding(innerPadding)
                    ) {
                        NotesListScreen(
                            currentRoute = topLevelBackStack.topLevelKey,
                            onNoteClick = { note -> topLevelBackStack.add(TopLevelRoute.NoteDetails) }
                        )
                    }
                }
                entry<TopLevelRoute.NoteDetails> {
                    Column(
                        Modifier
                            .padding(innerPadding)
                    ) {
                        NoteDetailsScreen()
                    }
                }
                entry<TopLevelRoute.CreateNote> {
                    Column(
                        Modifier
                            .padding(innerPadding)
                    ) {
                        CreateNoteScreen()
                    }
                }
                entry<TopLevelRoute.Settings> {
                    Column(
                        Modifier
                            .padding(innerPadding)
                    ) {
                        SettingsScreen()
                    }
                }
            },
        )
    }
}