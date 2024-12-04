package starwars.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import starwars.data.api.StarWarsApi
import starwars.data.models.BaseResource
import starwars.data.models.ErrorResponse
import starwars.data.models.Film
import starwars.data.models.Person
import starwars.data.models.Planet
import starwars.data.models.Species
import starwars.data.models.Starship
import starwars.data.models.Vehicle
import starwars.data.state.ResourceResult

class ResourcesPagingSource(
    private val resourceType: String,
    private val starWarsApi: StarWarsApi
): PagingSource<Int, Any>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return when (
            val result = handleApiCall {
                when (resourceType) {
                    "people" -> {
                        starWarsApi.getResources<Person>(resourceType, position)
                    }
                    "planets" -> {
                        starWarsApi.getResources<Planet>(resourceType, position)
                    }
                    "films" -> {
                        starWarsApi.getResources<Film>(resourceType, position)
                    }
                    "species" -> {
                        starWarsApi.getResources<Species>(resourceType, position)
                    }
                    "vehicles" -> {
                        starWarsApi.getResources<Vehicle>(resourceType, position)
                    }
                    "starships" -> {
                        starWarsApi.getResources<Starship>(resourceType, position)
                    }
                    else -> { ErrorResponse("Invalid resource type") }
                }
            }
        ) {
            is ResourceResult.Success -> {
                LoadResult.Page(
                    data = (result.data as BaseResource<Any>).results,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (result.data.results.isEmpty()) null else position + 1
                )
            }
            is ResourceResult.Error -> {
                LoadResult.Error(Throwable(result.data?.detail))
            }
            else -> {
                LoadResult.Page(data = emptyList(), 0, 0)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}