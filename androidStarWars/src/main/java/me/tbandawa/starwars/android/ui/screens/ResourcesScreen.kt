package me.tbandawa.starwars.android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import me.tbandawa.starwars.android.ui.components.NavigationToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesScreen(
    text: String,
    navController: NavController
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            NavigationToolbar(
                title = text,
                navController = navController,
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val list = (0..10).map { it.toString() }
                items(count = list.size) {
                    Text(
                        text = "Item $it",
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(top = 18.dp)
                    )
                }
            }
        }
    )

}