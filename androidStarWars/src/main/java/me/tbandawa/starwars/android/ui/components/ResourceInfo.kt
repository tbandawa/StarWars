package me.tbandawa.starwars.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import me.tbandawa.starwars.android.StarWarsTheme

@Composable
fun ResourceInfo(
    title: String,
    value: String,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .background(color = Color.Transparent)
            .height(75.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = MaterialTheme.colorScheme.surface)
        ) {

            val (titleView, valueView) = createRefs()

            Text(
                text = title,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(titleView) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )
            Text(
                text = value,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                ),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
                    .constrainAs(valueView) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResourceInfoPreview() {
    StarWarsTheme(darkTheme = true) {
        ResourceInfo("StarWas", "123", modifier = Modifier)
    }
}