package com.example.myapplication.features.shared.placelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.domain.model.Place

@Composable
fun PlaceListContet(
    modifier: Modifier = Modifier,
    place: Place
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = if (place.category.icon.isNotEmpty())
                rememberImagePainter(place.category.icon)
            else painterResource(R.drawable.fine),
            "place icon",
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .size(40.dp)
                .background(MaterialTheme.colors.primary),
            contentScale = ContentScale.FillHeight,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp),
            text = place.name
        )
    }
}
