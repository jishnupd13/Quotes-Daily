package com.app.activitiesapplication.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.activitiesapplication.domain.model.bottom_navigation.BottomNavigationItem
import com.app.activitiesapplication.domain.model.bottom_navigation.bottomNavigationItems
import com.app.activitiesapplication.ui.navigation.InnerNavGraph

@Composable
fun HomeScreen(){
  val navController = rememberNavController()
  Surface(
    modifier = Modifier.fillMaxSize(),
  ) {
    Scaffold(
      bottomBar = {
        BottomNavigationView(list = bottomNavigationItems, navController = navController)
      }) { innerPadding ->
      Surface (
        modifier = Modifier
          .padding(innerPadding)
          .fillMaxSize()
      ) {
        InnerNavGraph(navController)
      }
    }
  }
}

@Composable
fun BottomNavigationView(list: List<BottomNavigationItem>, navController: NavController) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar {
        list.forEach { item ->
            val selected = currentRoute == item.navPath
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.navPath) {
                            // Avoid building up a large stack of the same destinations
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    TabBarIconView(
                        isSelected = selected,
                        selectedIcon = item.selectedIcon,
                        unselectedIcon = item.unselectedIcon,
                        title = item.title,
                        badgeAmount = item.badgeCount,
                    )
                },
                label = { Text(item.title) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(
  isSelected: Boolean,
  selectedIcon: ImageVector,
  unselectedIcon: ImageVector,
  title: String,
  badgeAmount: Int? = null
) {
  BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
    Icon(
      imageVector = if (isSelected) {selectedIcon} else {unselectedIcon},
      contentDescription = title
    )
  }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TabBarBadgeView(count: Int? = null) {
  if (count != null) {
    Badge {
      Text(count.toString())
    }
  }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
  HomeScreen()
}