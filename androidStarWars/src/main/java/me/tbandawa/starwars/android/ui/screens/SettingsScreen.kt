package me.tbandawa.starwars.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
            }
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
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxWidth()
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
                                Image(
                                    painter = painterResource(R.drawable.ic_palette),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp, 20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Theme",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = if (isDarkTheme) "Dark" else "Light",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 16.sp
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxWidth()
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
                                Image(
                                    painter = painterResource(R.drawable.ic_code),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp, 20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Github",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Image(
                                    painter = painterResource(R.drawable.ic_right_chevron),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(11.dp, 11.dp)
                                )
                            }

                            Spacer(
                                modifier = Modifier
                                    .height(0.25.dp)
                                    .fillMaxWidth()
                                    .padding(start = 40.dp)
                                    .background(color = Color.LightGray)
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable {
                                        uriHandler.openUri("https://icons8.com/")
                                    }
                                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_image),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp, 20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Icons",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Image(
                                    painter = painterResource(R.drawable.ic_right_chevron),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(11.dp, 11.dp)
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
    SettingsScreen(
        isDarkTheme = true
    ) { }
}