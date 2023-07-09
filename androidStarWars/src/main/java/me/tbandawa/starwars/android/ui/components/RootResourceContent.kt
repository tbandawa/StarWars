package me.tbandawa.starwars.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import starwars.data.models.RootResource
import starwars.data.models.iterator

@Composable
fun RootResourceContent(
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