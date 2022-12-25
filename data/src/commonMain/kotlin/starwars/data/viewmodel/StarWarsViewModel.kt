package starwars.data.viewmodel

import io.ktor.client.plugins.logging.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import starwars.data.StarWarsSDK
import starwars.data.models.BaseResource
import starwars.data.models.ResourceResult

class StarWarsViewModel(private val starWarsSDK: StarWarsSDK): BaseViewModel() {

    private var isLoaded = false

    private val _resources = MutableStateFlow<ResourceResult<BaseResource>>(ResourceResult.Empty)
    val resources: StateFlow<ResourceResult<BaseResource>> = _resources

    fun getResources() {
        coroutineScope.launch {
            if (!isLoaded) {
                starWarsSDK.getRootResources().collect { results ->
                    _resources.value = results
                    if (results is ResourceResult.Success)
                        isLoaded = true
                }
            }
        }
    }

    @Suppress("unused")
    fun observeResources(provideNewState: ((ResourceResult<BaseResource>) -> Unit)) {
        _resources.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }
}