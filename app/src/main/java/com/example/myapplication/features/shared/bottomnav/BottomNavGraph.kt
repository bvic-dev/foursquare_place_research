package com.example.myapplication.features.shared.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.features.favoris.FavoritesScreen
import com.example.myapplication.features.map.MapsScreen
import com.example.myapplication.features.search.SearchScreen
import com.example.myapplication.features.settings.SettingScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = BottomNavItem.Map.route
    ) {
        composable(BottomNavItem.Map.route) {
            MapsScreen()
        }
        composable(BottomNavItem.Search.route) {
            SearchScreen()
        }
        composable(BottomNavItem.Favorites.route) {
            FavoritesScreen()
        }
        composable(BottomNavItem.Settings.route) {
            SettingScreen()
        }
    }
}
