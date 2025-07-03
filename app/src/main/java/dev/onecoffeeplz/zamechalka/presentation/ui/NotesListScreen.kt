package dev.onecoffeeplz.zamechalka.presentation.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.onecoffeeplz.zamechalka.R
import dev.onecoffeeplz.zamechalka.presentation.navigation.TopLevelBackStack
import dev.onecoffeeplz.zamechalka.presentation.navigation.TopLevelRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NotesListScreen(topLevelBackStack: TopLevelBackStack<Any>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(stringResource(R.string.notes_list))
        Button(onClick = { topLevelBackStack.add(TopLevelRoute.NoteDetails) }) {
            Text("Go to ...")
        }
    }
}