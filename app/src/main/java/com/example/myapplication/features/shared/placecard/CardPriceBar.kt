package com.example.myapplication.features.shared.placecard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Euro
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PriceBar(price: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        repeat(price) {
            Icon(
                imageVector = Icons.Rounded.Euro,
                contentDescription = "Euro",
                tint = Color.Gray,
                modifier = modifier.size(15.dp),
            )
        }
    }
}
