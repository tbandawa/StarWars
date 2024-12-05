package me.tbandawa.starwars.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.starwars.android.ui.components.RecentItem
import me.tbandawa.starwars.android.ui.components.SearchInput
import me.tbandawa.starwars.android.ui.components.ToolBar
import starwars.data.models.RootResource
import starwars.data.models.iterator
import starwars.data.state.ResourceResult
import starwars.data.viewmodel.RootViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: RootViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val rootResources by viewModel.rootResources.collectAsState()
        val resourceType = remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                ToolBar(
                    "Search",
                    scrollBehavior
                )
            }
        ) { it ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    SearchInput(
                        resourceType = resourceType.value,
                        onSearchResource = {

                        },
                        onCleared = {
                            resourceType.value = ""
                        }
                    )

                    AnimatedVisibility(resourceType.value.isEmpty()) {
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
                                                resourceType.value = it
                                            }
                                        }
                                    }
                                    else -> {}
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}