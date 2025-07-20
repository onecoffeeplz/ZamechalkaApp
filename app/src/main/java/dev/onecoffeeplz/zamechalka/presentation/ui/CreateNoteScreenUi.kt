package dev.onecoffeeplz.zamechalka.presentation.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dev.onecoffeeplz.zamechalka.R
import dev.onecoffeeplz.zamechalka.presentation.event.CreateNoteEvent
import dev.onecoffeeplz.zamechalka.presentation.ui.components.RecordPermissionView
import dev.onecoffeeplz.zamechalka.presentation.ui.components.ErrorView
import dev.onecoffeeplz.zamechalka.presentation.viewmodel.CreateNoteViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CreateNoteScreenUi(
    viewModel: CreateNoteViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState,
) {
    val state by viewModel.state.collectAsState()

    val audioPermissionState = rememberPermissionState(android.Manifest.permission.RECORD_AUDIO)

    var noteName by remember { mutableStateOf("") }

    if (state.isRecording) {
        ShowRecordingView(
            onStopRecording = { viewModel.onEvent(CreateNoteEvent.StopRecording) }
        )
    } else if (state.recordingFilePath != null) {
        RecordingSaveForm(
            recordingFilePath = state.recordingFilePath!!,
            noteName = noteName,
            onNoteNameChange = { noteName = it },
            onSaveClick = {
                viewModel.onEvent(
                    CreateNoteEvent.SaveRecording(
                        noteName,
                        state.recordingFilePath!!,
                        state.recordingDuration,
                    )
                )
            },
            onCancelClick = {
                viewModel.onEvent(CreateNoteEvent.DeleteRecording(state.recordingFilePath!!))
            },
        )
    } else {
        if (audioPermissionState.status.isGranted) {
            ShowStartRecordingView(
                onStartRecording = { viewModel.onEvent(CreateNoteEvent.StartRecording) }
            )
        } else {
            RecordPermissionView(audioPermissionState)
        }
    }

    if (state.recordWasSaved) {
        val recordSavedMessage = stringResource(R.string.note_was_saved_in_db)
        LaunchedEffect(true) {
            snackbarHostState.showSnackbar(
                message = recordSavedMessage,
                duration = SnackbarDuration.Short
            )
            viewModel.onEvent(CreateNoteEvent.Idle)
        }
    }

    if (state.audioWasDeleted) {
        val audioDeletedMessage = stringResource(R.string.audio_was_deleted)
        LaunchedEffect(true) {
            snackbarHostState.showSnackbar(
                message = audioDeletedMessage,
                duration = SnackbarDuration.Short
            )
            viewModel.onEvent(CreateNoteEvent.Idle)
        }
    }

    state.error?.let {
        ErrorView(message = it)
    }
}

@Composable
private fun ShowRecordingView(onStopRecording: () -> Unit) {

    val btnSize = 192.dp
    val totalTimeSec = 30
    val progress = remember { Animatable(0f) }
    var timeLeftSec by remember { mutableIntStateOf(totalTimeSec) }
    val arcColor = MaterialTheme.colorScheme.error

    LaunchedEffect(Unit) {
        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = totalTimeSec * 1000, easing = LinearEasing)
        )
    }

    LaunchedEffect(progress.value) {
        timeLeftSec = ((1f - progress.value) * totalTimeSec).toInt().coerceAtLeast(0)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Canvas(modifier = Modifier.size(btnSize)) {
                val strokeWidth = 12.dp.toPx()
                drawArc(
                    color = Color.LightGray,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(strokeWidth, cap = StrokeCap.Round)
                )
            }

            Canvas(modifier = Modifier.size(btnSize)) {
                val strokeWidth = 12.dp.toPx()
                drawArc(
                    color = arcColor,
                    startAngle = -90f,
                    sweepAngle = 360f * progress.value,
                    useCenter = false,
                    style = Stroke(strokeWidth, cap = StrokeCap.Round)
                )
            }

            Button(
                onClick = onStopRecording,
                modifier = Modifier
                    .size(btnSize),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color.White),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.img_recording),
                    contentDescription = stringResource(R.string.stop),
                    modifier = Modifier
                        .size(btnSize * 0.8f),
                    contentScale = ContentScale.Crop,
                )
            }

            Box(
                modifier = Modifier
                    .size(btnSize * 0.4f)
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        clip = false,
                        ambientColor = MaterialTheme.colorScheme.error,
                        spotColor = MaterialTheme.colorScheme.error,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.error,
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,

                ) {
                Text(
                    text = timeLeftSec.toString(),
                    color = MaterialTheme.colorScheme.onError,
                    fontWeight = FontWeight.Bold,
                    fontSize = (btnSize.value * 0.1f).sp
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.recording),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun ShowStartRecordingView(onStartRecording: () -> Unit) {

    val btnSize = 192.dp
    val infiniteTransition = rememberInfiniteTransition()

    val glowColor by infiniteTransition.animateColor(
        initialValue = Color(0x804CAF50),
        targetValue = Color(0xFF4CAF50),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val glowRadius by infiniteTransition.animateFloat(
        initialValue = 12f,
        targetValue = 32f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = onStartRecording,
            modifier = Modifier
                .size(btnSize)
                .shadow(
                    elevation = glowRadius.dp,
                    shape = CircleShape,
                    clip = false,
                    ambientColor = glowColor,
                    spotColor = glowColor,
                ),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(Color.White),
        ) {
            Image(
                painter = painterResource(R.drawable.img_recording),
                contentDescription = stringResource(R.string.start_recording),
                modifier = Modifier
                    .size(btnSize * 0.8f),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.push_to_start_recording),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun RecordingSaveForm(
    recordingFilePath: String,
    noteName: String,
    onNoteNameChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val btnSize = 192.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(64.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = stringResource(R.string.save_note),
            modifier = Modifier
                .size(btnSize)
                .shadow(
                    elevation = 8.dp,
                    shape = CircleShape,
                    clip = true
                )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.recorded_file, recordingFilePath),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelSmall.copy(fontStyle = FontStyle.Italic),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = noteName,
            onValueChange = onNoteNameChange,
            label = { Text(stringResource(R.string.enter_note_name)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                onClick = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onSaveClick()
                }) {
                Text(
                    text = stringResource(R.string.save_note),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                onClick = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onCancelClick()
                }) {
                Text(
                    text = stringResource(R.string.cancel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ShowRecordingViewPreview() {
    ShowRecordingView(onStopRecording = {})
}

@Composable
@Preview(showBackground = true)
private fun ShowStartRecordingViewPreview() {
    ShowStartRecordingView(onStartRecording = {})
}

@Preview(showBackground = true)
@Composable
fun RecordingSaveFormPreview() {
    val recordingFilePath = "/storage/emulated/0/recordings/audio123.wav"
    var noteName by remember { mutableStateOf("") }

    RecordingSaveForm(
        recordingFilePath = recordingFilePath,
        noteName = noteName,
        onNoteNameChange = { noteName = it },
        onSaveClick = { },
        onCancelClick = { },
    )
}
