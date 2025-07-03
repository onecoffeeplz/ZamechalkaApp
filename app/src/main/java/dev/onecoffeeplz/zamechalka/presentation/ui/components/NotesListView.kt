package dev.onecoffeeplz.zamechalka.presentation.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import dev.onecoffeeplz.zamechalka.domain.model.Note

@Composable
fun NotesListView(notes: List<Note>, onNoteClick: (Note) -> Unit) {
    LazyColumn {
        items(notes) { note ->
            NoteItem(note = note, onClick = { onNoteClick(note) })
        }
    }
}