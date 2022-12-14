package me.tbandawa.starwars.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.starwars.android.R

@Composable
fun ResourceItem(
    title: String
) {
    Card(
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .background(color = Color.White)
            .height(130.dp)
            .width(70.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {

            val (titleView, iconView) = createRefs()

            Text(
                text = title,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(titleView) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )
            Image(
                painter = painterResource(R.drawable.ic_right),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .padding(8.dp)
                    .constrainAs(iconView) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResourceItemPreview() {
    ResourceItem("StarWas")
}