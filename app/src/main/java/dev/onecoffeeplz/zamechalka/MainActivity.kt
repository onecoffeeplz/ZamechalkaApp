package dev.onecoffeeplz.zamechalka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.onecoffeeplz.zamechalka.presentation.ui.CreateNoteScreen
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
    Scaffold(
        topBar = { ZamechalkaTopBar() }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            CreateNoteScreen()
        }
    }
}