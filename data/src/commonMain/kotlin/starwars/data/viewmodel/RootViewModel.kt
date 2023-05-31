package starwars.data.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import starwars.data.models.RootResource
import starwars.data.repo.StarWarsRepo
import starwars.data.state.ResourceResult

class RootViewModel(private val starWarsRepo: StarWarsRepo): BaseViewModel() {

    private var isRootLoaded = false

    private val _rootResources = MutableStateFlow<ResourceResult<RootResource>>(ResourceResult.Empty)
    val rootResources: StateFlow<ResourceResult<RootResource>> = _rootResources

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

    @Suppress("unused")
    fun observeRootResources(provideNewState: ((ResourceResult<RootResource>) -> Unit)) {
        _rootResources.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }
}