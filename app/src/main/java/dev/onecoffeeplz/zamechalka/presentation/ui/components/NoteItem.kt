package dev.onecoffeeplz.zamechalka.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.onecoffeeplz.zamechalka.domain.model.Note

@Composable
fun NoteItem(note: Note, onClick: () -> Unit) {
    Text(
        text = note.name,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    )
}