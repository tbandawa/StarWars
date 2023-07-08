package me.tbandawa.starwars.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.starwars.android.R
import me.tbandawa.starwars.android.ui.components.BottomNavigationBar
import starwars.data.viewmodel.RootViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
            HomeScreen(
                viewModel,
                navController
            )
        }
        composable(route = NavigationItem.Search.route) {
            SearchScreen(viewModel)
        }
        composable(route = NavigationItem.Settings.route) {
            SettingsScreen()
        }
        composable(route = "resources/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            ResourcesScreen(
                title!!,
                navController
            )
        }
    }
}

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "StarWars")
    object Search : NavigationItem("search", R.drawable.ic_search, "Search")
    object Settings : NavigationItem("settings", R.drawable.ic_settings ,"Settings")
}

/*
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}*/
