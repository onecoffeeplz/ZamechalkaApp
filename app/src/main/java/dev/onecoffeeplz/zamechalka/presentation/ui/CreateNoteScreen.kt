package dev.onecoffeeplz.zamechalka.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dev.onecoffeeplz.zamechalka.R
import dev.onecoffeeplz.zamechalka.presentation.event.CreateNoteEvent
import dev.onecoffeeplz.zamechalka.presentation.ui.components.ShowToast
import dev.onecoffeeplz.zamechalka.presentation.viewmodel.CreateNoteViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CreateNoteScreen(viewModel: CreateNoteViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()

    val audioPermissionState = rememberPermissionState(android.Manifest.permission.RECORD_AUDIO)

    var noteName by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(64.dp)
    ) {
        if (state.isRecording) {
            Button(onClick = { viewModel.onEvent(CreateNoteEvent.StopRecording) }) {
                Text(stringResource(R.string.stop))
            }
            Text(
                stringResource(
                    R.string.recording_with_duration,
                    SimpleDateFormat("mm:ss", Locale.getDefault()).format(
                        state.recordingDuration
                    )
                )
            )
        } else {
            if (audioPermissionState.status.isGranted) {
                Button(onClick = { viewModel.onEvent(CreateNoteEvent.StartRecording) }) {
                    Text(stringResource(R.string.start_recording))
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(R.string.audio_recording_permission_is_required), color = Color.Red)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { audioPermissionState.launchPermissionRequest() }) {
                        Text(stringResource(R.string.grant_permission))
                    }
                }
            }
        }

        state.recordingFilePath?.let {
            Text(stringResource(R.string.recorded_file, it))

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = noteName,
                onValueChange = { noteName = it },
                label = { Text(stringResource(R.string.enter_note_name)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                keyboardController?.hide()
                focusManager.clearFocus()
                viewModel.onEvent(
                    CreateNoteEvent.SaveRecording(
                        noteName,
                        state.recordingFilePath!!,
                        state.recordingDuration,
                    )
                )
            }) {
                Text(stringResource(R.string.save_note))
            }
        }

        if (state.recordWasSaved) {
            ShowToast(stringResource(R.string.note_was_saved_in_db))
        }

        state.error?.let {
            Text(stringResource(R.string.error, it), color = Color.Red)
        }
    }
}