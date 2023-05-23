package starwars.data.viewmodel

import io.ktor.client.plugins.logging.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import starwars.data.StarWarsRepo
import starwars.data.models.RootResource
import starwars.data.models.BaseResource
import starwars.data.models.ErrorResponse
import starwars.data.models.Film
import starwars.data.state.ResourceResult

class StarWarsViewModel(private val starWarsRepo: StarWarsRepo): BaseViewModel() {

    private var isRootLoaded = false

    private val _rootResources = MutableStateFlow<ResourceResult<RootResource>>(ResourceResult.Empty)
    val rootResources: StateFlow<ResourceResult<RootResource>> = _rootResources

    private val _resourceItems = MutableStateFlow<ResourceResult<Any>>(ResourceResult.Empty)
    val resourceItems: StateFlow<ResourceResult<Any>> = _resourceItems

    private val _resourceItem = MutableStateFlow<ResourceResult<Any>>(ResourceResult.Empty)
    val resourceItem: StateFlow<ResourceResult<Any>> = _resourceItems

    fun getRootResources() {
        coroutineScope.launch {
            if (!isRootLoaded) {
                starWarsRepo.getRootResources().collect { results ->
                    _rootResources.value = results
                    if (results is ResourceResult.Success)
                        isRootLoaded = true
                }
            }
        }
    }

    fun getResources(resourceType: String, page: Int) {
        coroutineScope.launch {
            starWarsRepo.getResources(resourceType, page).collect { results ->
                _resourceItems.value = results
            }
        }
    }

    fun getResource(resourceType: String, resourceId: Int) {
        coroutineScope.launch {
            starWarsRepo.getResource(resourceType, resourceId).collect { results ->
                _resourceItem.value = results
            }
        }
    }

    @Suppress("unused")
    fun observeRootResources(provideNewState: ((ResourceResult<RootResource>) -> Unit)) {
        _rootResources.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }

    @Suppress("unused")
    fun observeResourceItems(provideNewState: ((ResourceResult<Any>) -> Unit)) {
        _resourceItems.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }

    @Suppress("unused")
    fun observeResourceItem(provideNewState: ((ResourceResult<Any>) -> Unit)) {
        _resourceItem.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }
}