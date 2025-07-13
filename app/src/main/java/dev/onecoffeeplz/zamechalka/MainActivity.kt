package dev.onecoffeeplz.zamechalka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.rememberNavBackStack
import dev.onecoffeeplz.zamechalka.navigation.NavigationRoot
import dev.onecoffeeplz.zamechalka.navigation.TOP_LEVEL_ROUTES
import dev.onecoffeeplz.zamechalka.navigation.TopLevelRoute
import dev.onecoffeeplz.zamechalka.presentation.ui.components.ZamechalkaTopBar
import dev.onecoffeeplz.zamechalka.presentation.ui.theme.ZamechalkaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZamechalkaTheme {
                val topLevelBackStack = rememberNavBackStack(TopLevelRoute.NotesListScreen)
                Scaffold(
                    topBar = { ZamechalkaTopBar() },
                    bottomBar = {
                        NavigationBar {
                            TOP_LEVEL_ROUTES.forEach { topLevelRoute ->
                                val isSelected = topLevelRoute == topLevelBackStack.lastOrNull()
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = {
                                        topLevelBackStack.add(topLevelRoute)
                                    },
                                    icon = {
                                        topLevelRoute.icon?.let {
                                            Icon(
                                                imageVector = it,
                                                contentDescription = null
                                            )
                                        }
                                    },
                                    label = {
                                        Text(text = stringResource(id = topLevelRoute.label))
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavigationRoot(
                        topLevelBackStack = topLevelBackStack,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}