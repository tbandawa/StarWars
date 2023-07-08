package me.tbandawa.starwars.android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import me.tbandawa.starwars.android.ui.components.RootResourceItem
import me.tbandawa.starwars.android.ui.components.ToolBar
import starwars.data.models.RootResource
import starwars.data.state.ResourceResult
import starwars.data.models.iterator
import starwars.data.viewmodel.RootViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    starWarsViewModel: RootViewModel,
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val viewModel = remember { starWarsViewModel }.also { it.getRootResources() }
        val rootResources by viewModel.rootResources.collectAsState()

        Scaffold(
            topBar = {
                ToolBar(
                    "StarWars",
                    scrollBehavior
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                when (rootResources) {
                    is ResourceResult.Empty -> {}
                    is ResourceResult.Loading -> {
                        LoadingContent()
                    }
                    is ResourceResult.Success -> {
                        val rootResource = (rootResources as ResourceResult.Success<RootResource>).data
                        ResourceContent(
                            rootResource,
                            navController
                        )
                    }
                    is ResourceResult.Error -> {
                        RetryContent(
                            errorMessage = (rootResources as ResourceResult.Error).data?.detail!!,
                            retry = { viewModel.getRootResources() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(width = 35.dp, height = 35.dp)
            )
            Text(
                text = "Loading...",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun ResourceContent(
    resource: RootResource,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(resource.iterator().size) { index ->
            Card(
                modifier = Modifier
                    .clickable {
                        navController.navigate("resources/${resource.iterator()[index].first}")
                    }
                    .padding(4.dp)
            ) {
                RootResourceItem(title = resource.iterator()[index].first)
            }
        }
    }
}

@Composable
fun RetryContent(
    errorMessage: String,
    retry: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = errorMessage,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            )
            Button(
                onClick = {
                    retry.invoke()
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(35.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    text = "Retry",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}
