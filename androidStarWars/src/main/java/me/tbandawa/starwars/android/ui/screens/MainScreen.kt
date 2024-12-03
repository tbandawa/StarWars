package me.tbandawa.starwars.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.starwars.android.R
import me.tbandawa.starwars.android.ui.components.BottomNavigationBar
import org.koin.androidx.compose.koinViewModel
import starwars.data.viewmodel.ResourceViewModel
import starwars.data.viewmodel.ResourcesViewModel
import starwars.data.viewmodel.RootViewModel
import starwars.data.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val rootViewModel: RootViewModel = koinViewModel()
    val resourcesViewModel: ResourcesViewModel = koinViewModel()
    val resourceViewModel: ResourceViewModel = koinViewModel()
    val searchViewModel: SearchViewModel = koinViewModel()

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                MainNavigation(
                    navController = navController,
                    rootViewModel = rootViewModel, resourcesViewModel, resourceViewModel, searchViewModel
                )
            }
        },
        bottomBar = { BottomNavigationBar(navController) }
    )
}

@Composable
fun MainNavigation(
    navController: NavHostController,
    rootViewModel: RootViewModel,
    resourcesViewModel: ResourcesViewModel,
    resourceViewModel: ResourceViewModel,
    searchViewModel: SearchViewModel
) {
    NavHost(
        navController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(
                navController = navController,
                viewModel = rootViewModel
            )
        }
        composable(route = NavigationItem.Search.route) {
            SearchScreen(
                viewModel = rootViewModel
            )
        }
        composable(route = NavigationItem.Settings.route) {
            SettingsScreen()
        }
        composable(route = "resources/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            ResourcesScreen(
                type = title!!,
                navController = navController,
                viewModel = resourcesViewModel
            )
        }
        composable(route = "resource/{id}/{type}/{title}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val type = backStackEntry.arguments?.getString("type")
            val title = backStackEntry.arguments?.getString("title")
            ResourceScreen(
                id = id!!,
                type = type!!,
                title = title!!,
                navController = navController,
                viewModel = resourceViewModel
            )
        }
    }
}

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "StarWars")
    object Search : NavigationItem("search", R.drawable.ic_search, "Search")
    object Settings : NavigationItem("settings", R.drawable.ic_settings ,"Settings")
}
