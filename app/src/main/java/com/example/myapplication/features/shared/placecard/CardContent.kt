package com.example.myapplication.features.shared.placecard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.myapplication.R

@Composable
fun CardContent(
    placeDetailCardViewModel: PlaceDetailCardViewModel
) {
    Image(
        painter = if (!placeDetailCardViewModel.placePhoto.value.isNullOrEmpty())
            rememberImagePainter(placeDetailCardViewModel.placePhoto.value)
        else painterResource(R.drawable.fine),
        "place photo",
        contentScale = ContentScale.FillHeight,
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .heightIn(194.dp, 194.dp)
            .widthIn(344.dp, 344.dp)
    )
    Spacer(Modifier.size(10.dp))
    CardText(
        text = placeDetailCardViewModel.placeAddress.value ?: "",
        textStyle = MaterialTheme.typography.subtitle1,
        color = Color.Gray
    )
}
