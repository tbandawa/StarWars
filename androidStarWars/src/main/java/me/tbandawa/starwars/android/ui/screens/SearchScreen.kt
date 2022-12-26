package me.tbandawa.starwars.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.starwars.android.ui.components.RecentItem
import me.tbandawa.starwars.android.ui.components.ToolBar
import starwars.data.models.BaseResource
import starwars.data.models.ResourceResult
import starwars.data.models.iterator
import starwars.data.viewmodel.StarWarsViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    starWarsViewModel: StarWarsViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val viewModel = remember { starWarsViewModel }
        val resources by viewModel.resources.collectAsState()

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

                    //val data = listOf("Films", "People", "Planets", "Species", "Starships", "Vehicles")
                    var searchText by remember { mutableStateOf(TextFieldValue("")) }

                    TextField(
                        value = searchText,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "searchIcon",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )
                        },
                        onValueChange = {
                            searchText = it
                        },
                        textStyle = TextStyle.Default.copy(fontSize = 15.sp),
                        placeholder = {
                            Text(
                                text = "Search",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )

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
                                .height(0.25.dp)
                                .fillMaxWidth()
                                .background(color = Color.LightGray)
                        )

                        LazyColumn {
                            when (resources) {
                                is ResourceResult.Success -> {
                                    val baseResource = (resources as ResourceResult.Success<BaseResource>).data
                                    items(baseResource.iterator().size) { index ->
                                        RecentItem(title = baseResource.iterator()[index].first.replaceFirstChar { char ->
                                            if (char.isLowerCase()) char.titlecase(
                                                Locale.getDefault()
                                            ) else char.toString()
                                        })
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

/*@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}*/