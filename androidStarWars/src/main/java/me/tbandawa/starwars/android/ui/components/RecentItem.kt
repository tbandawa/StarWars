package me.tbandawa.starwars.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecentItem(
    title: String
) {
    Column(
        modifier = Modifier
            .clickable {

            }
    ) {

        Text(
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .padding(top = 6.dp, bottom = 4.dp)
        )

        Spacer(
            modifier = Modifier
                .height(0.25.dp)
                .fillMaxWidth()
                .background(color = Color.LightGray)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun RecentItemPreview() {
    RecentItem("People")
}