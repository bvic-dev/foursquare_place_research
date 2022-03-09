package com.example.myapplication.features.shared.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

open class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var route: String
) {
    object Map : BottomNavItem(
        title = "Map",
        icon = Icons.Rounded.Map,
        route = "map"
    )

    object Search : BottomNavItem(
        title = "Search",
        icon = Icons.Rounded.Search,
        route = "search"
    )

    object Favorites : BottomNavItem(
        title = "Favorites",
        icon = Icons.Rounded.Favorite,
        route = "favorites"
    )

    object Settings : BottomNavItem(
        title = "Settings",
        icon = Icons.Rounded.Settings,
        route = "settings"
    )
}
