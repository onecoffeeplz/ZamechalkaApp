package dev.onecoffeeplz.zamechalka.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.onecoffeeplz.zamechalka.R
import dev.onecoffeeplz.zamechalka.domain.model.Note
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NoteItem(note: Note, onClick: () -> Unit, onDelete: (Note) -> Unit) {

    val density = LocalDensity.current
    val screenWidthPx = with(density) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val thresholdPx = 3 * screenWidthPx / 4f

    var deleted by remember { mutableStateOf(false) }
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart && !deleted) {
                deleted = true
                onDelete(note)
                true
            } else {
                it != SwipeToDismissBoxValue.StartToEnd
            }
        },
        positionalThreshold = { thresholdPx }
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        SwipeToDismissBox(
            state = swipeToDismissBoxState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            backgroundContent = @Composable {
                when (swipeToDismissBoxState.dismissDirection) {
                    SwipeToDismissBoxValue.EndToStart -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.tertiary),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = stringResource(R.string.delete_note),
                                tint = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier
                                    .padding(end = 24.dp)
                            )
                        }
                    }

                    else -> {}
                }
            }
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                border = BorderStroke(0.25.dp, MaterialTheme.colorScheme.onPrimaryContainer),
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = note.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = stringResource(
                            R.string.duration,
                            SimpleDateFormat("mm:ss", Locale.getDefault()).format(
                                note.duration
                            )
                        ),
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 8.dp),
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(
                            R.string.created,
                            SimpleDateFormat("dd.MM.y", Locale.getDefault()).format(
                                note.createdAt
                            )
                        ),
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteItemPreview() {
    val previewNote = Note(
        13,
        name = "Test note",
        path = "",
        duration = 5011,
        text = "",
        tags = "",
        createdAt = 1751440432674,
    )
    NoteItem(
        note = previewNote,
        onClick = {},
        onDelete = {}
    )
}