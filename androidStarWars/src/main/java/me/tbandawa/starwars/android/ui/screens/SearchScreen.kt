package me.tbandawa.starwars.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import me.tbandawa.starwars.android.ui.components.RecentItem
import me.tbandawa.starwars.android.ui.components.SearchInput
import me.tbandawa.starwars.android.ui.components.SearchResults
import me.tbandawa.starwars.android.ui.components.ToolBar
import starwars.data.models.RootResource
import starwars.data.models.iterator
import starwars.data.state.ResourceResult
import starwars.data.viewmodel.RootViewModel
import starwars.data.viewmodel.SearchViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    rootViewModel: RootViewModel,
    searchViewModel: SearchViewModel,
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val rootResources by rootViewModel.rootResources.collectAsState()
        var resourceType by rememberSaveable { mutableStateOf("") }
        var searchQuery by rememberSaveable { mutableStateOf("") }
        var isSearching by rememberSaveable { mutableStateOf(false) }

        Scaffold(
            topBar = {
                ToolBar(
                    "Search",
                    scrollBehavior
                )
            }
        ) { it ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(it)
                    .padding(horizontal = 16.dp)
            ) {

                // Search input
                SearchInput(
                    resourceType = resourceType,
                    onSearchResource = { query ->
                        if (resourceType.isNotEmpty() && query.isNotEmpty()) {
                            searchQuery = query
                            searchViewModel.getPagedSearchResults(resourceType, searchQuery)

                            isSearching = true
                        }
                    },
                    onCleared = {
                        resourceType = ""
                        searchQuery = ""
                        isSearching = false
                    }
                )

                // Search filter, hides if resourceType is selected
                AnimatedVisibility(resourceType.isEmpty()) {
                    Column {
                        Spacer(modifier = Modifier.height(25.dp))
                        Text(
                            text = "Search Filter",
                            style = TextStyle(
                                color = Color.LightGray,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.onSurface)
                        )
                        LazyColumn {
                            when (rootResources) {
                                is ResourceResult.Success -> {
                                    val rootResource = (rootResources as ResourceResult.Success<RootResource>).data
                                    items(rootResource.iterator().size) { index ->
                                        RecentItem(title = rootResource.iterator()[index].first.replaceFirstChar { char ->
                                            if (char.isLowerCase()) char.titlecase(
                                                Locale.getDefault()
                                            ) else char.toString()
                                        }) {
                                            resourceType = it
                                        }
                                    }
                                }
                                else -> {}
                            }
                        }
                    }
                }

                // Search results
                if (isSearching) {
                    SearchResults(resourceType, searchQuery, searchViewModel) { resourceItem ->
                        navController.navigate("resource/${resourceItem.id}/${resourceItem.type}/${resourceItem.name}")
                    }
                }
            }
        }
    }
}