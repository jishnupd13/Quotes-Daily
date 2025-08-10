package com.app.quotes_daily.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.quotes_daily.ui.daily_quote.DailyQuoteScreen
import com.app.quotes_daily.ui.favourite.FavouriteScreen
import com.app.quotes_daily.ui.quotes.Quotes

object BottomNavigationDestinations {
    const val QUOTES = "quotes"
    const val DAILY_QUOTE = "daily_quote"
    const val FAVORITES = "favorites"
}

@Composable
fun InnerNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = BottomNavigationDestinations.QUOTES){

        composable(BottomNavigationDestinations.QUOTES){
            // Quotes Screen
            Quotes()
        }

        composable(BottomNavigationDestinations.DAILY_QUOTE){
            //Daily Quote Screen
            DailyQuoteScreen()
        }

        composable(BottomNavigationDestinations.FAVORITES){
            // Favourite Screen
            FavouriteScreen()
        }
    }
}