package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.features.shared.bottomnav.BottomNavigationBar
import com.example.myapplication.features.shared.bottomnav.NavigationGraph

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        content = {
            // Add padding because bottombar is overlapping the content
            // https://foso.github.io/Jetpack-Compose-Playground/material/scaffold/#tips
            Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
                NavigationGraph(navController = navController)
            }
        }
    )
}
