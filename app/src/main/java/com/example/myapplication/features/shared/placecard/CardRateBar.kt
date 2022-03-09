package com.example.myapplication.features.shared.placecard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.myapplication.features.shared.theme.Typography
import com.example.myapplication.features.shared.theme.starsColor

@Composable
fun RateBar(rate: Float, modifier: Modifier = Modifier) {
    val totalStars = rate.toInt().coerceAtMost(5)
    val halfStars =
        if ((rate * 10).toInt() > totalStars * 10 && (rate * 10).toInt() < (totalStars + 1) * 10)
            1 else 0
    val emptyStars = (5 - totalStars - halfStars).coerceAtLeast(0)
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CardText(
            text = "$rate",
            color = Color.Gray,
            textStyle = Typography.subtitle1
        )
        Spacer(Modifier.size(5.dp))
        repeat(totalStars) {
            Star(
                starIcon = Icons.Rounded.Star
            )
        }
        repeat(halfStars) {
            Star(
                starIcon = Icons.Rounded.StarHalf
            )
        }
        repeat(emptyStars) {
            Star(
                starIcon = Icons.Rounded.StarOutline
            )
        }
    }
}

@Composable
private fun Star(starIcon: ImageVector, modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier
            .size(20.dp),
        imageVector = starIcon,
        contentDescription = null,
        tint = starsColor
    )
}
