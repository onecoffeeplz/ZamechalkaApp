package dev.onecoffeeplz.zamechalka.presentation.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.onecoffeeplz.zamechalka.R
import dev.onecoffeeplz.zamechalka.domain.model.Note
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NoteDetailsScreen(note: Note) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = {},
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Spacer(Modifier.width(8.dp))
            Text(
                note.name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )

        }
        Spacer(Modifier.height(16.dp))
        Row {
            Text(
                text = stringResource(
                    R.string.created,
                    SimpleDateFormat("dd.MM.y", Locale.getDefault()).format(
                        note.createdAt
                    )
                ),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium,
            )
        }

        Spacer(Modifier.height(16.dp))
        IconButton(onClick = {}) {
            Icon(Icons.Default.PlayArrow, contentDescription = "Play", Modifier.size(512.dp))
        }
        Text(
            text = stringResource(
                R.string.duration,
                SimpleDateFormat("mm:ss", Locale.getDefault()).format(
                    note.duration
                )
            ),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsScreenPreview() {
    val previewNote = Note(
        13,
        name = "Test note",
        path = "",
        duration = 5011,
        text = "",
        tags = "",
        createdAt = 1751440432674,
    )
    NoteDetailsScreen(previewNote)
}