package me.tbandawa.starwars.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.starwars.android.StarWarsTheme

@Composable
fun RecentItem(
    title: String,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onItemClick.invoke(title)
            }
    ) {

        Text(
            text = title,
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .padding(top = 6.dp, bottom = 4.dp)
        )

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onSurface)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun RecentItemPreview() {
    StarWarsTheme(darkTheme = true) {
        RecentItem("People") {}
    }
}