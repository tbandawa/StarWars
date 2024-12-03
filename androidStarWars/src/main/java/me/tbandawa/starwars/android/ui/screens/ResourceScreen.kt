package me.tbandawa.starwars.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import me.tbandawa.starwars.android.ui.components.FilmContent
import me.tbandawa.starwars.android.ui.components.LoadingContent
import me.tbandawa.starwars.android.ui.components.NavigationToolbar
import me.tbandawa.starwars.android.ui.components.PersonContent
import me.tbandawa.starwars.android.ui.components.PlanetContent
import me.tbandawa.starwars.android.ui.components.RetryContent
import me.tbandawa.starwars.android.ui.components.SpeciesContent
import me.tbandawa.starwars.android.ui.components.StarshipContent
import me.tbandawa.starwars.android.ui.components.VehicleContent
import starwars.data.models.Film
import starwars.data.models.Person
import starwars.data.models.Planet
import starwars.data.models.Species
import starwars.data.models.Starship
import starwars.data.models.Vehicle
import starwars.data.state.ResourceResult
import starwars.data.viewmodel.ResourceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourceScreen(
    id: String,
    type: String,
    title: String,
    navController: NavController,
    viewModel: ResourceViewModel
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val resourceItem by viewModel.resourceItem.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getResource(type, id.toInt())
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.dismissJob()
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            NavigationToolbar(
                title = title,
                navController = navController,
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (resourceItem) {
                is ResourceResult.Empty -> { }
                is ResourceResult.Loading -> {
                    LoadingContent()
                }
                is ResourceResult.Success -> {
                    OpenResource(item = (resourceItem as ResourceResult.Success).data)
                }
                is ResourceResult.Error -> {
                    RetryContent(
                        errorMessage = (resourceItem as ResourceResult.Error).data?.detail!!,
                        retry = { viewModel.getResource(type, id.toInt()) }
                    )
                }
            }
        }
    }
}

@Composable
fun OpenResource(item: Any) {
    return when (item) {
        is Person -> {
            PersonContent(person = item)
        }
        is Starship -> {
            StarshipContent(starship = item)
        }
        is Planet -> {
            PlanetContent(planet = item)
        }
        is Vehicle -> {
            VehicleContent(vehicle = item)
        }
        is Species -> {
            SpeciesContent(species = item)
        }
        is Film -> {
            FilmContent(film = item)
        }
        else -> {
            Text(text = "Unknown Item")
        }
    }
}