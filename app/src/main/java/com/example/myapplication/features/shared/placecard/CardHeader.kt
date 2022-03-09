package com.example.myapplication.features.shared.placecard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.myapplication.R

@Composable
fun CardHeader(
    modifier: Modifier = Modifier,
    placeDetailCardViewModel: PlaceDetailCardViewModel
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = if (!placeDetailCardViewModel.placeIcon.value.isNullOrEmpty())
                rememberImagePainter(placeDetailCardViewModel.placeIcon.value)
            else painterResource(R.drawable.fine),
            "place icon",
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .size(40.dp)
                .background(MaterialTheme.colors.primary),
            contentScale = ContentScale.FillHeight,
        )
        Spacer(Modifier.size(10.dp))
        Column(
            modifier = Modifier
                .weight(
                    weight = 1f,
                    fill = false
                )
                .fillMaxWidth(),
        ) {
            CardText(
                text = placeDetailCardViewModel.placeName.value ?: "",
                textStyle = MaterialTheme.typography.h6
            )
            CardText(
                text = placeDetailCardViewModel.placeDescription.value ?: "",
                textStyle = MaterialTheme.typography.subtitle1,
                color = Color.Gray
            )
        }
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = "close",
            tint = Color.Gray,
            modifier = Modifier
                .clickable {
                    placeDetailCardViewModel.onClickCancelCard()
                }
                .padding(start = 10.dp)
        )
    }
}
