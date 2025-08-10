package com.app.quotes_daily.domain.model.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.ui.graphics.vector.ImageVector
import com.app.quotes_daily.ui.navigation.BottomNavigationDestinations

//data class for navigation items in the bottom navigation bar
data class BottomNavigationItem(
    val id: Int,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val navPath: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        id = 1,
        title = "Quotes",
        selectedIcon = Icons.Filled.MailOutline,
        unselectedIcon = Icons.Filled.MailOutline,
        navPath = BottomNavigationDestinations.QUOTES
    ),
    BottomNavigationItem(
        id = 2,
        title = "Daily Quote",
        selectedIcon = Icons.Filled.AccountBox,
        unselectedIcon = Icons.Filled.AccountBox,
        navPath = BottomNavigationDestinations.DAILY_QUOTE

    ),
    BottomNavigationItem(
        id = 3,
        title = "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Default.FavoriteBorder,
        navPath =  BottomNavigationDestinations.FAVORITES
    )
)