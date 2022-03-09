package com.example.myapplication.features.shared.searchbar

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun SearchBarTextField(
    modifier: Modifier = Modifier,
    isEnable: Boolean,
    view: View,
    addressSearchBarViewModel: AddressSearchBarViewModel
) {
    OutlinedTextField(
        enabled = isEnable,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface, CircleShape),
        value = addressSearchBarViewModel.searchQuery.value ?: "",
        onValueChange = { query ->
            addressSearchBarViewModel.onSearchValueChanged(query)
        },
        isError = addressSearchBarViewModel.searchError.value != null,
        placeholder = { Text(text = "Search an address") },
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = {
                addressSearchBarViewModel.onClearSearch()
                view.clearFocus()
            }) {
                Icon(
                    imageVector = Icons.Filled.Clear, contentDescription = "Clear"
                )
            }
        },
        keyboardActions = KeyboardActions(
            onSearch = {
                addressSearchBarViewModel.onSearch()
                view.clearFocus()
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        shape = CircleShape
    )
}
