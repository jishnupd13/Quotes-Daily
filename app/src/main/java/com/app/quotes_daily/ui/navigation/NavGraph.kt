package com.app.quotes_daily.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.quotes_daily.ui.home.HomeScreen
import com.app.quotes_daily.ui.posts.PostListScreen
import com.app.quotes_daily.ui.settings.SettingsScreen
import com.app.quotes_daily.ui.splash.SplashScreen

object Destinations {
    const val POSTS = "posts"
    const val SETTINGS = "settings"
    const val SPLASH = "splash"
    const val HOME = "home"
}

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {

    NavHost(navController = navController, startDestination = Destinations.SPLASH, modifier = modifier) {

        composable(Destinations.POSTS) {
            PostListScreen(onNavigateToSettings = {
                navController.navigate(Destinations.SETTINGS)
            })
        }
        composable(Destinations.SETTINGS) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }

        composable(Destinations.SPLASH){
             SplashScreen{
                 navController.navigate(Destinations.HOME){
                     popUpTo(Destinations.SPLASH) {
                         inclusive = true
                     }
                 }
             }
        }

        composable(Destinations.HOME){
            HomeScreen()
        }
    }
}
