package dev.onecoffeeplz.zamechalka.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
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

    SwipeToDismissBox(
        state = swipeToDismissBoxState,
        modifier = Modifier.fillMaxSize(),
        backgroundContent = @Composable {
            when (swipeToDismissBoxState.dismissDirection) {
                SwipeToDismissBoxValue.EndToStart -> {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove item",
                        modifier = Modifier
                            .fillMaxSize()
                            .width(72.dp)
                            .background(MaterialTheme.colorScheme.error)
                            .wrapContentSize(Alignment.CenterEnd)
                            .padding(12.dp),
                        tint = MaterialTheme.colorScheme.onError
                    )
                }

                else -> {}
            }
        }
    ) {
        ListItem(
            headlineContent = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onClick)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = note.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = onClick),
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
                                color = MaterialTheme.colorScheme.secondary,
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
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.labelMedium,
                            )
                        }
                    }
                }
            }
        )
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