package dev.onecoffeeplz.zamechalka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.onecoffeeplz.zamechalka.presentation.ui.CreateNoteScreen
import dev.onecoffeeplz.zamechalka.presentation.ui.theme.ZamechalkaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZamechalkaTheme {
                CreateNoteScreen()
            }
        }
    }
}
