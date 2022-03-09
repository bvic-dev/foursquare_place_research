package com.example.myapplication.features.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Loader(isLoading: Boolean, modifier: Modifier = Modifier) {
    if (isLoading) {
        AnimatedVisibility(
            modifier = modifier.fillMaxSize(),
            visible = isLoading,
            enter = EnterTransition.None,
            exit = fadeOut()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
            )
        }
    }
}
