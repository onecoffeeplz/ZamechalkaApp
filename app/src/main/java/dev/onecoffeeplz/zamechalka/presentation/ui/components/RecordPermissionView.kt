package dev.onecoffeeplz.zamechalka.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import dev.onecoffeeplz.zamechalka.R

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun RecordPermissionView(audioPermissionState: PermissionState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        OutlinedCard(
            modifier = Modifier
                .wrapContentSize(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            border = BorderStroke(
                0.25.dp,
                MaterialTheme.colorScheme.onPrimaryContainer
            ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_need_access),
                    contentDescription = stringResource(R.string.audio_recording_permission_is_required),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.audio_recording_permission_is_required),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { audioPermissionState.launchPermissionRequest() }) {
                    Text(stringResource(R.string.grant_permission))
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Preview(showBackground = true)
fun RecordPermissionViewPreview() {
    val mockPermissionState = object : PermissionState {
        override val permission: String = android.Manifest.permission.RECORD_AUDIO
        override val status: PermissionStatus = PermissionStatus.Granted
        override fun launchPermissionRequest() { }
    }
    RecordPermissionView(mockPermissionState)
}