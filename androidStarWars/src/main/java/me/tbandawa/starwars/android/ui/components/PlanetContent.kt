package me.tbandawa.starwars.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.starwars.android.ui.screens.parseDate
import starwars.data.models.Planet

@Composable
fun PlanetContent(planet: Planet) {

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ResourceInfo(title = "Climate", value = planet.climate, Modifier.weight(1f))
            ResourceInfo(title = "Gravity", value = planet.gravity, Modifier.weight(1f))
            ResourceInfo(title = "Population", value = planet.population, Modifier.weight(1f))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ResourceInfo(title = "Rotation Period", value = planet.rotation_period, Modifier.weight(1f))
            ResourceInfo(title = "Orbital Period", value = planet.orbital_period, Modifier.weight(1f))
            ResourceInfo(title = "Diameter", value = planet.diameter, Modifier.weight(1f))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ResourceInfo(title = "Terrain", value = planet.terrain, Modifier.weight(1f))
            ResourceInfo(title = "Surface Water", value = planet.surface_water, Modifier.weight(1f))
        }

        Text(
            text = "Residents",
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 4.dp)
        )
        planet.residents.forEach {
            LinkInfo(url = it)
        }

        Text(
            text = "Films",
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 4.dp)
        )
        planet.films.forEach {
            LinkInfo(url = it)
        }

        Text(
            text = "Last Edited",
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        Text(
            text = parseDate(planet.edited),
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

    }
}