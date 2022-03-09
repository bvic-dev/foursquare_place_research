package com.example.myapplication.features.shared.bottomnav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Map,
        BottomNavItem.Search,
        BottomNavItem.Favorites,
        BottomNavItem.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    BottomNavigation {
        items.forEach { item ->
            AddItem(
                item = item,
                currentRoute = currentRoute,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    item: BottomNavItem,
    currentRoute: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = {
            Text(
                text = item.title
            )
        },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title
            )
        },
        selected = currentRoute?.route == item.route,
        alwaysShowLabel = true,
        unselectedContentColor = LocalContentColor.current.copy(
            alpha = ContentAlpha.disabled
        ),
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }
    )
}
