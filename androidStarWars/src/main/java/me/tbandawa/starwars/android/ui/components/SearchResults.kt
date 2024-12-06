package me.tbandawa.starwars.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import me.tbandawa.starwars.android.ui.screens.ResourceItem
import me.tbandawa.starwars.android.ui.screens.getItemInfo
import starwars.data.viewmodel.SearchViewModel

@Composable
fun SearchResults(
    resourceType: String,
    searchQuery: String,
    searchViewModel: SearchViewModel,
    openResource: (ResourceItem) -> Unit
) {

    val pagingItems = searchViewModel.pagedSearchResults.collectAsLazyPagingItems()

    LazyColumn {
        items(pagingItems.itemCount) { index ->
            val resourceItem = getItemInfo(pagingItems[index]!!)
            Card(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(vertical = 8.dp)
                    .clickable {
                        openResource.invoke(resourceItem)
                    },
                shape = RoundedCornerShape(8.dp)
            ) {
                ResourceItem(name = resourceItem.name, date = resourceItem.date)
            }
        }
        pagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingContent() }
                }
                loadState.refresh is LoadState.Error -> {
                    val error = pagingItems.loadState.refresh as LoadState.Error
                    item { RetryContent(errorMessage = error.error.message!!, retry = { retry() } ) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingMore() }
                }
                loadState.append is LoadState.Error -> {
                    val error = pagingItems.loadState.append as LoadState.Error
                    item { LoadingMoreError(message = error.error.message!!) { retry() } }
                }
            }
        }
    }
}