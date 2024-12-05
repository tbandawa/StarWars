package me.tbandawa.starwars.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.tbandawa.starwars.android.StarWarsTheme

@Composable
fun SearchInput(
    resourceType: String,
    onSearchResource: (Array<String>) -> Unit,
    onCleared: () -> Unit
) {

    var searchText by rememberSaveable { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "searchIcon",
                modifier = Modifier
                    .size(25.dp)
            )
            if (resourceType.isNotEmpty()) {
                Text(
                    text = resourceType,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .background(color = Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(4.dp)
                )
            }
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchResource(arrayOf(resourceType, searchText))
                    }
                ),
                singleLine = true,
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "searchIcon",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        searchText = ""
                        onCleared.invoke()
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchInputPreview() {
    StarWarsTheme(darkTheme = false) {
        SearchInput(
            resourceType = "People",
            onSearchResource = {},
            onCleared = {}
        )
    }
}