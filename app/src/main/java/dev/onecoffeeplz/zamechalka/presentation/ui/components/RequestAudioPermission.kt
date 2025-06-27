package dev.onecoffeeplz.zamechalka.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestAudioPermission(onPermissionGranted: () -> Unit) {
    val permissionState =
        rememberPermissionState(permission = android.Manifest.permission.RECORD_AUDIO)

    LaunchedEffect(Unit) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()
        } else {
            onPermissionGranted()
        }
    }

    when {
        permissionState.status.isGranted -> {
            onPermissionGranted()
        }

        permissionState.status.shouldShowRationale -> {
            Text("Для записи голосовых заметок необходимо дать разрешение на использование микрофона.")
        }

        else -> {
            Text("Разрешение отклонено. Пожалуйста, разрешите использование микрофона в настройках.")
        }
    }

}
