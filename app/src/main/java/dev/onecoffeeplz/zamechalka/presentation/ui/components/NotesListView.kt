package dev.onecoffeeplz.zamechalka.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.presentation.ui.utils.hideKeyboardAndClearFocusOnOutsideClick

@Composable
fun NotesListView(notes: List<Note>, onNoteClick: (Note) -> Unit, onSwipeToDelete: (Note) -> Unit) {
    val textFieldState = rememberTextFieldState()
    val query = textFieldState.text.toString()
    val filteredNotes = if (query.isBlank()) notes else {
        notes.filter { note ->
            note.name.contains(query, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier.hideKeyboardAndClearFocusOnOutsideClick()
    ) {

        ZamechalkaSearchBar(
            textFieldState = textFieldState,
            onQueryChange = { textFieldState.edit { replace(0, length, it) } },
            onSearch = {},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(8.dp))

        LazyColumn {
            items(filteredNotes, key = { it.id!! }) { note ->
                NoteItem(
                    note = note,
                    onClick = { onNoteClick(note) },
                    onDelete = { onSwipeToDelete(note) })
            }
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
    NotesListView(previewNotes, {}, {})
}