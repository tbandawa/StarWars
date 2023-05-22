package starwars.data.viewmodel

import io.ktor.client.plugins.logging.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import starwars.data.StarWarsRepo
import starwars.data.models.BaseResource
import starwars.data.models.BaseResult
import starwars.data.models.ErrorResponse
import starwars.data.models.Film
import starwars.data.models.Films
import starwars.data.state.ResourceResult

class StarWarsViewModel(private val starWarsRepo: StarWarsRepo): BaseViewModel() {

    private var isRootLoaded = false

    private val _resources = MutableStateFlow<ResourceResult<BaseResource>>(ResourceResult.Empty)
    val resources: StateFlow<ResourceResult<BaseResource>> = _resources

    private val _resourceItems = MutableStateFlow<ResourceResult<Any>>(ResourceResult.Empty)
    val resourceItems: StateFlow<ResourceResult<Any>> = _resourceItems

    fun getRootResources() {
        coroutineScope.launch {
            if (!isRootLoaded) {
                starWarsRepo.getRootResources().collect { results ->
                    _resources.value = results
                    if (results is ResourceResult.Success)
                        isRootLoaded = true
                }
            }
        }
    }

    fun getResources(resourceType: String, page: Int) {
        coroutineScope.launch {
            when (resourceType) {
                "people" -> {
                    starWarsRepo.getPeople(resourceType, page).collect { results ->
                        _resourceItems.value = results
                    }
                }
                "planets" -> {
                    starWarsRepo.getPlanets(resourceType, page).collect { results ->
                        _resourceItems.value = results
                    }
                }
                "films" -> {
                    starWarsRepo.getFilms(resourceType, page).collect { results ->
                        _resourceItems.value = results
                    }
                }
                "species" -> {
                    starWarsRepo.getSpecies(resourceType, page).collect { results ->
                        _resourceItems.value = results
                    }
                }
                "vehicles" -> {
                    starWarsRepo.getVehicles(resourceType, page).collect { results ->
                        _resourceItems.value = results
                    }
                }
                "starships" -> {
                    starWarsRepo.getStarships(resourceType, page).collect { results ->
                        _resourceItems.value = results
                    }
                }
                else -> { _resourceItems.value = ResourceResult.Error(ErrorResponse("Invalid resource type")) }
            }
        }
    }

    @Suppress("unused")
    fun observeResources(provideNewState: ((ResourceResult<BaseResource>) -> Unit)) {
        _resources.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }

    @Suppress("unused")
    fun observeResourceItems(provideNewState: ((ResourceResult<Any>) -> Unit)) {
        _resourceItems.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }
}