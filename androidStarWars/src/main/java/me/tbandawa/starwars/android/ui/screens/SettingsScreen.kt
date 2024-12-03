package me.tbandawa.starwars.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.starwars.android.R
import me.tbandawa.starwars.android.StarWarsTheme
import me.tbandawa.starwars.android.ui.components.ToolBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    changeTheme: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        val uriHandler = LocalUriHandler.current
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            topBar = {
                ToolBar(
                    "Settings",
                    scrollBehavior
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    Card(
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable {
                                        changeTheme.invoke()
                                    }
                                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp, 20.dp),
                                    tint = MaterialTheme.colorScheme.primary,
                                    painter = painterResource(R.drawable.ic_palette),
                                    contentDescription = "Cover"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Theme",
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = if (isDarkTheme) "Dark" else "Light",
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 16.sp
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Card(
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable {
                                        uriHandler.openUri("https://github.com/tbandawa/StarWars")
                                    }
                                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp, 20.dp),
                                    tint = MaterialTheme.colorScheme.primary,
                                    painter = painterResource(R.drawable.ic_code),
                                    contentDescription = "Cover"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Github",
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    modifier = Modifier
                                        .size(11.dp, 11.dp),
                                    tint = MaterialTheme.colorScheme.primary,
                                    painter = painterResource(R.drawable.ic_right_chevron),
                                    contentDescription = "Cover"
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable {
                                        uriHandler.openUri("https://icons8.com/")
                                    }
                                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp, 20.dp),
                                    tint = MaterialTheme.colorScheme.primary,
                                    painter = painterResource(R.drawable.ic_image),
                                    contentDescription = "Cover"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Icons",
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    modifier = Modifier
                                        .size(11.dp, 11.dp),
                                    tint = MaterialTheme.colorScheme.primary,
                                    painter = painterResource(R.drawable.ic_right_chevron),
                                    contentDescription = "Cover"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    StarWarsTheme(darkTheme = false) {
        SettingsScreen(
            isDarkTheme = true
        ) { }
    }
}