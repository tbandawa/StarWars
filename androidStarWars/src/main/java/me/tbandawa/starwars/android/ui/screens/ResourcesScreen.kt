package me.tbandawa.starwars.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.tbandawa.starwars.android.ui.components.LoadingContent
import me.tbandawa.starwars.android.ui.components.NavigationToolbar
import me.tbandawa.starwars.android.ui.components.ResourceItem
import me.tbandawa.starwars.android.ui.components.RetryContent
import starwars.data.models.Film
import starwars.data.models.Person
import starwars.data.models.Planet
import starwars.data.models.Species
import starwars.data.models.Starship
import starwars.data.models.Vehicle
import starwars.data.state.ResourceResult
import starwars.data.viewmodel.ResourcesViewModel
import java.text.SimpleDateFormat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesScreen(
    type: String,
    navController: NavController,
    viewModel: ResourcesViewModel
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val resourcesResults by viewModel.resourceResults.collectAsState()
    val resourcesItems by viewModel.resourceItems.collectAsState()

    LaunchedEffect(Unit) {
        if (type != viewModel.resourceName || viewModel.resultsList.size == 0) {
            viewModel.getResources(type, 1)
        }
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
                title = type,
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
            when (resourcesResults) {
                is ResourceResult.Empty -> { }
                is ResourceResult.Loading -> {
                    LoadingContent()
                }
                is ResourceResult.Success -> {
                    LazyColumn {
                        items(resourcesItems) { item ->

                            val itemInfo = getItemInfo(item)

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                                    .clickable {

                                    },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                ResourceItem(name = itemInfo[0], date = itemInfo[1])
                            }
                        }
                    }
                }
                is ResourceResult.Error -> {
                    RetryContent(
                        errorMessage = (resourcesResults as ResourceResult.Error).data?.detail!!,
                        retry = {  }
                    )
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun getItemInfo(item: Any): Array<String> {

    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("MMMM dd, yyyy")

    return when (item) {
        is Person -> {
            arrayOf(item.name, formatter.format(parser.parse(item.edited)), item.url)
        }
        is Starship -> {
            arrayOf(item.name, formatter.format(parser.parse(item.edited)), item.url)
        }
        is Planet -> {
            arrayOf(item.name, formatter.format(parser.parse(item.edited)), item.url)
        }
        is Vehicle -> {
            arrayOf(item.name, formatter.format(parser.parse(item.edited)), item.url)
        }
        is Species -> {
            arrayOf(item.name, formatter.format(parser.parse(item.edited)), item.url)
        }
        is Film -> {
            arrayOf(item.title, formatter.format(parser.parse(item.edited)), item.url)
        }
        else -> {
            arrayOf("", "", "")
        }
    }
}