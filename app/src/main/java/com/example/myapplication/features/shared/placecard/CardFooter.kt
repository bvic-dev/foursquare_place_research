package com.example.myapplication.features.shared.placecard

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun CardFooter(
    modifier: Modifier = Modifier,
    placeDetailCardViewModel: PlaceDetailCardViewModel
) {
    val context = LocalContext.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RateBar(
            rate = placeDetailCardViewModel.placeRating.value ?: 0f,
        )
        Spacer(Modifier.size(10.dp))
        PriceBar(
            price = placeDetailCardViewModel.placePrice.value ?: 0
        )
        Spacer(Modifier.weight(1f))
        Icon(
            modifier = Modifier.clickable {
                Toast.makeText(
                    context,
                    if (placeDetailCardViewModel.isFavorite.value == true)
                        "Removed from favorites"
                    else
                        "Added to favorites",
                    Toast.LENGTH_SHORT
                ).show()
                placeDetailCardViewModel.onClickFavorite()
            },
            imageVector = Icons.Rounded.Favorite,
            contentDescription = "favorite",
            tint = if (placeDetailCardViewModel.isFavorite.value == true) Color.Red else Color.Gray
        )
    }
}
