package me.tbandawa.starwars.android.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import me.tbandawa.starwars.android.ui.screens.NavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Search,
        NavigationItem.Settings
    )

    NavigationBar(
        tonalElevation = 10.dp,
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->

            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(
                            id = item.icon
                        ),
                        item.title,
                        modifier = Modifier
                            .size(25.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp
                    )
                },
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Black.copy(0.4f),
                    unselectedTextColor = Color.Black.copy(0.4f)
                ),
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })

        }

    }
}