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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.onecoffeeplz.zamechalka.R

@Composable
fun EmptyView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        OutlinedCard(
            modifier = Modifier
                .wrapContentSize(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            border = BorderStroke(0.25.dp, MaterialTheme.colorScheme.onPrimaryContainer),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_nothing_found),
                    contentDescription = stringResource(R.string.no_notes_found),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    stringResource(R.string.no_notes_found),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyViewPreview() {
    EmptyView()
}