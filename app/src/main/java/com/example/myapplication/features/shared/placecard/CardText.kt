package com.example.myapplication.features.shared.placecard

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun CardText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
    color: Color = textStyle.color
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = textStyle.fontSize,
        color = color,
        fontStyle = textStyle.fontStyle,
        fontWeight = textStyle.fontWeight,
    )
}
