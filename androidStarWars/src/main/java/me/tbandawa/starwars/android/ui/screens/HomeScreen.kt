package me.tbandawa.starwars.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import me.tbandawa.starwars.android.ui.components.LoadingContent
import me.tbandawa.starwars.android.ui.components.RetryContent
import me.tbandawa.starwars.android.ui.components.RootResourceContent
import me.tbandawa.starwars.android.ui.components.ToolBar
import org.koin.androidx.compose.koinViewModel
import starwars.data.models.RootResource
import starwars.data.state.ResourceResult
import starwars.data.viewmodel.RootViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    rootViewModel: RootViewModel = koinViewModel(),
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val viewModel = remember { rootViewModel }.also { it.getRootResources() }
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
                        RootResourceContent(
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
