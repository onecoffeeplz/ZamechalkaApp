package dev.onecoffeeplz.zamechalka.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.onecoffeeplz.zamechalka.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZamechalkaSearchBar(
    textFieldState: TextFieldState,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onExpandedChange: (Boolean) -> Unit = {},
) {
    val query = textFieldState.text.toString()
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = { onQueryChange(it) },
                    onSearch = {
                        onSearch(query)
                        focusManager.clearFocus()
                    },
                    expanded = expanded,
                    onExpandedChange = onExpandedChange,
                    placeholder = { Text(stringResource(R.string.search)) },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                textFieldState.edit { replace(0, length, "") }
                                focusManager.clearFocus()
                            }
                        ) {
                            if (query.isNotBlank()) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = stringResource(R.string.clear)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = stringResource(R.string.search)
                                )
                            }
                        }
                    })
            },
            expanded = expanded,
            onExpandedChange = onExpandedChange,
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun ZamechalkaSearchBarPreview() {
    ZamechalkaSearchBar(
        TextFieldState(),
        onSearch = {},
        onQueryChange = {},
    )
}