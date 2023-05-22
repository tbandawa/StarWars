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
import starwars.data.models.Film
import starwars.data.models.Films
import starwars.data.state.ResourceResult

class StarWarsViewModel(private val starWarsRepo: StarWarsRepo): BaseViewModel() {

    private var isRootLoaded = false

    private val _resources = MutableStateFlow<ResourceResult<BaseResource>>(ResourceResult.Empty)
    val resources: StateFlow<ResourceResult<BaseResource>> = _resources

    private val _resourceItems = MutableStateFlow<ResourceResult<Any>>(ResourceResult.Empty)
    val resourceItems: StateFlow<ResourceResult<Any>> = _resourceItems

    fun getResources() {
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

    fun getResources(resourceType: String) {
        coroutineScope.launch {
            starWarsRepo.getResources(resourceType).collect { results ->
                _resourceItems.value = results
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