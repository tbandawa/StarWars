package me.tbandawa.starwars.android.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.tbandawa.starwars.android.StarWarsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior
) {
    MediumTopAppBar(
        title = {
            Text(
                text = title
            )
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ToolBarPreview() {
    StarWarsTheme(darkTheme = true) {
        ToolBar("StarWas", TopAppBarDefaults.pinnedScrollBehavior())
    }
}