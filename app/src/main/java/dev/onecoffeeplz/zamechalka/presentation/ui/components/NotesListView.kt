package dev.onecoffeeplz.zamechalka.presentation.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.onecoffeeplz.zamechalka.domain.model.Note

@Composable
fun NotesListView(notes: List<Note>, onNoteClick: (Note) -> Unit) {
    LazyColumn {
        items(notes) { note ->
            NoteItem(note = note, onClick = { onNoteClick(note) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListViewPreview() {
    val previewNotes = listOf(
        Note(
            7,
            name = "Test note",
            path = "",
            duration = 2006,
            text = "",
            tags = "",
            createdAt = 1751440432674,
        ),
        Note(
            13,
            name = "To remember",
            path = "",
            duration = 5011,
            text = "",
            tags = "",
            createdAt = 1751140132674,
        )
    )
    NotesListView(previewNotes, {})
}