package dev.onecoffeeplz.zamechalka.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.onecoffeeplz.zamechalka.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZamechalkaTopBar() {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    )
}

@Preview(showBackground = true)
@Composable
fun ZamechalkaTopBarPreview() {
    ZamechalkaTopBar()
}