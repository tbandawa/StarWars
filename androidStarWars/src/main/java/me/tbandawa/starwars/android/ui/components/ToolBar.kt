package me.tbandawa.starwars.android.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToolBar(
    title: String
) {

    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp,
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp
                )
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ToolBarPreview() {
    ToolBar("StarWas")
}