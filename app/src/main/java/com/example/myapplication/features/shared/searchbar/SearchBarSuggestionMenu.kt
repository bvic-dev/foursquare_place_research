package com.example.myapplication.features.shared.searchbar

import android.view.View
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.PopupProperties

@Composable
fun SearchBarSuggestionMenu(
    modifier: Modifier = Modifier,
    addressSearchBarViewModel: AddressSearchBarViewModel,
    view: View
) {
    DropdownMenu(
        modifier = modifier,
        expanded = !addressSearchBarViewModel.suggestionList.value.isNullOrEmpty(),
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        ),
        onDismissRequest = { addressSearchBarViewModel.onClearSuggestion() }
    ) {
        addressSearchBarViewModel.suggestionList.value?.forEach { text ->
            DropdownMenuItem(onClick = {
                addressSearchBarViewModel.onClickSuggestion(text)
                view.clearFocus()
            }) {
                Text(
                    text = text ?: ""
                )
            }
        }
    }
}
