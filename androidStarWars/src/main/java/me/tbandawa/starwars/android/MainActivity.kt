package me.tbandawa.starwars.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.starwars.android.ui.screens.MainScreen
import org.koin.core.component.KoinComponent

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val backGroundLight = Color.White
    val surfaceLight = Color.Black
    val textHeadingLight = Color.Black
    var textInfoLight = Color.Black

    val backGroundDark = Color.Black
    val surfaceDark = Color.Gray
    val textHeadingDark = Color.White
    var textInfoDark = Color.White

    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFBB86FC),
            onSurfaceVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6200EE),
            onSurfaceVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MainScreen()
    }
}
