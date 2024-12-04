package me.tbandawa.starwars.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import me.tbandawa.starwars.android.ui.components.LoadingContent
import me.tbandawa.starwars.android.ui.components.LoadingMore
import me.tbandawa.starwars.android.ui.components.LoadingMoreError
import me.tbandawa.starwars.android.ui.components.NavigationToolbar
import me.tbandawa.starwars.android.ui.components.ResourceItem
import me.tbandawa.starwars.android.ui.components.RetryContent
import starwars.data.models.Film
import starwars.data.models.Person
import starwars.data.models.Planet
import starwars.data.models.Species
import starwars.data.models.Starship
import starwars.data.models.Vehicle
import starwars.data.viewmodel.ResourcesViewModel
import java.text.SimpleDateFormat

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesScreen(
    type: String,
    navController: NavController,
    viewModel: ResourcesViewModel
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val pagingItems: LazyPagingItems<Any> = when (type) {
        "people" -> viewModel.pagedPeopleResources.collectAsLazyPagingItems()
        "planets" -> viewModel.pagedPlanetsResources.collectAsLazyPagingItems()
        "films" -> viewModel.pagedFilmsResources.collectAsLazyPagingItems()
        "species" -> viewModel.pagedSpeciesResources.collectAsLazyPagingItems()
        "vehicles" -> viewModel.pagedVehiclesResources.collectAsLazyPagingItems()
        "starships" -> viewModel.pagedStarshipsResources.collectAsLazyPagingItems()
        else -> viewModel.pagedPeopleResources.collectAsLazyPagingItems()
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

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(pagingItems.itemCount) { index ->
                val resourceItem = getItemInfo(pagingItems[index]!!)
                Card(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                        .clickable {
                            navController.navigate("resource/${resourceItem.id}/${resourceItem.type}/${resourceItem.name}")
                        },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    ResourceItem(name = resourceItem.name, date = resourceItem.date)
                }
            }
            pagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingContent() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val error = pagingItems.loadState.refresh as LoadState.Error
                        item { RetryContent(errorMessage = error.error.message!!, retry = { retry() } ) }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingMore() }
                    }
                    loadState.append is LoadState.Error -> {
                        val error = pagingItems.loadState.append as LoadState.Error
                        item { LoadingMoreError(message = error.error.message!!) { retry() } }
                    }
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun getItemInfo(item: Any): ResourceItem {
    return when (item) {
        is Person -> {
            ResourceItem(getResourceId(item.url), item.name, parseDate(item.edited), getResourceType(item.url))
        }
        is Starship -> {
            ResourceItem(getResourceId(item.url), item.name, parseDate(item.edited), getResourceType(item.url))
        }
        is Planet -> {
            ResourceItem(getResourceId(item.url), item.name, parseDate(item.edited), getResourceType(item.url))
        }
        is Vehicle -> {
            ResourceItem(getResourceId(item.url), item.name, parseDate(item.edited), getResourceType(item.url))
        }
        is Species -> {
            ResourceItem(getResourceId(item.url), item.name, parseDate(item.edited), getResourceType(item.url))
        }
        is Film -> {
            ResourceItem(getResourceId(item.url), item.title, parseDate(item.edited), getResourceType(item.url))
        }
        else -> {
            ResourceItem(0, "", "", "")
        }
    }
}

fun getResourceId(url: String): Int {
    val segments = url.split("/")
    return segments[segments.size - 2].toInt()
}

fun getResourceType(url: String): String {
    val segments = url.split("/")
    return segments[segments.size - 3]
}

fun parseDate(date: String) : String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("MMMM dd, yyyy")
    return formatter.format(parser.parse(date))
}

data class ResourceItem(
    val id: Int,
    val name: String,
    val date: String,
    val type: String
)