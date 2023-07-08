package me.tbandawa.starwars.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ResourceItem(
    name: String,
    date: String
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(Color.Black)
            .padding(start = 8.dp)
    ) {

        val (titleText, messageText) = createRefs()

        Text(
            text = name,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .constrainAs(titleText) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(start = 0.dp, top = 10.dp, bottom = 5.dp)
        )

        Text(
            text = date,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .constrainAs(messageText) {
                    start.linkTo(parent.start)
                    top.linkTo(titleText.bottom)
                }
                .padding(start = 0.dp, bottom = 10.dp)
        )

    }

}

@Preview
@Composable
fun ResourceItemPreview() {
    ResourceItem(
        name = "Resource Name",
        date = "Resource Date"
    )
}