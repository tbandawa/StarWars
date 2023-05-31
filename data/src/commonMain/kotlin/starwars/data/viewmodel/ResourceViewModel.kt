package starwars.data.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import starwars.data.repo.StarWarsRepo
import starwars.data.state.ResourceResult

class ResourceViewModel(private val starWarsRepo: StarWarsRepo): BaseViewModel() {

    private val _resourceItem = MutableStateFlow<ResourceResult<Any>>(ResourceResult.Empty)
    val resourceItem: StateFlow<ResourceResult<Any>> = _resourceItem

    fun getResource(resourceType: String, resourceId: Int) {
        coroutineScope.launch {
            starWarsRepo.getResource(resourceType, resourceId).collect { results ->
                _resourceItem.value = results
            }
        }
    }

    @Suppress("unused")
    fun observeResourceItem(provideNewState: ((ResourceResult<Any>) -> Unit)) {
        _resourceItem.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }
}