package starwars.data.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import starwars.data.repo.StarWarsRepo
import starwars.data.state.ResourceResult

class ResourcesViewModel(private val starWarsRepo: StarWarsRepo): BaseViewModel() {

    private val _resourceItems = MutableStateFlow<ResourceResult<Any>>(ResourceResult.Empty)
    val resourceItems: StateFlow<ResourceResult<Any>> = _resourceItems

    fun getResources(resourceType: String, page: Int) {
        coroutineScope.launch {
            starWarsRepo.getResources(resourceType, page).collect { results ->
                _resourceItems.value = results
            }
        }
    }

    @Suppress("unused")
    fun observeResourceItems(provideNewState: ((ResourceResult<Any>) -> Unit)) {
        _resourceItems.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }
}