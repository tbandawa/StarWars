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
import starwars.data.models.Vehicle

@Composable
fun VehicleContent(vehicle: Vehicle) {

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
            ResourceInfo(title = "Model", value = vehicle.model, Modifier.weight(1f))
            ResourceInfo(title = "Manufacturer", value = vehicle.manufacturer, Modifier.weight(1f))
            ResourceInfo(title = "Cost In Credits", value = vehicle.cost_in_credits, Modifier.weight(1f))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ResourceInfo(title = "Length", value = vehicle.length, Modifier.weight(1f))
            ResourceInfo(title = "Max Atmospheric Speed", value = vehicle.max_atmosphering_speed, Modifier.weight(1f))
            ResourceInfo(title = "Crew", value = vehicle.crew, Modifier.weight(1f))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ResourceInfo(title = "Passengers", value = vehicle.passengers, Modifier.weight(1f))
            ResourceInfo(title = "Cargo Capacity", value = vehicle.cargo_capacity, Modifier.weight(1f))
            ResourceInfo(title = "Consumables", value = vehicle.consumables, Modifier.weight(1f))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ResourceInfo(title = "Vehicle Class", value = vehicle.vehicle_class, Modifier.weight(1f))
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
        vehicle.films.forEach {
            LinkInfo(url = it)
        }

        Text(
            text = "Pilots",
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 4.dp)
        )
        vehicle.pilots.forEach {
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
            text = parseDate(vehicle.edited),
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