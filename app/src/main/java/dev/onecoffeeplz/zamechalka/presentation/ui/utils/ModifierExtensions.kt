package dev.onecoffeeplz.zamechalka.presentation.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

fun Modifier.hideKeyboardAndClearFocusOnOutsideClick(): Modifier = composed {
    val focusManager = LocalFocusManager.current
    val controller = LocalSoftwareKeyboardController.current
    this.then(
        Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            controller?.hide()
            focusManager.clearFocus()
        }
    )
}