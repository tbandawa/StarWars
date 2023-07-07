package me.tbandawa.starwars.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import me.tbandawa.starwars.android.R
import starwars.data.viewmodel.RootViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: RootViewModel
) {

    val navController = rememberNavController()

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                MainNavigation(
                    viewModel = viewModel,
                    navController = navController
                )
            }
        },
        bottomBar = { BottomNavigationBar(navController) }
    )
}

@Composable
fun MainNavigation(
    viewModel: RootViewModel,
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(viewModel)
        }
        composable(route = NavigationItem.Search.route) {
            SearchScreen(viewModel)
        }
        composable(route = NavigationItem.Settings.route) {
            SettingsScreen()
        }
        composable(route = NavigationItem.Resources.route) {
            SettingsScreen()
        }
    }
}

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

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "StarWars")
    object Search : NavigationItem("search", R.drawable.ic_search, "Search")
    object Settings : NavigationItem("settings", R.drawable.ic_settings ,"Settings")
    object Resources : NavigationItem("resources", R.drawable.ic_home ,"Resources")
}

/*
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}*/
