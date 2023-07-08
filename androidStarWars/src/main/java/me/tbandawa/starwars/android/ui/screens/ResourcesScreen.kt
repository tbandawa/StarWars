package me.tbandawa.starwars.android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.tbandawa.starwars.android.ui.components.NavigationToolbar
import me.tbandawa.starwars.android.ui.components.ResourceItem

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

                    Card(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 16.dp, top = 2.dp, end = 16.dp, bottom = 2.dp)
                            .clickable {

                            },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        ResourceItem(name = "Name $it", date = "Date $it")
                    }

                }
            }
        }
    )

}