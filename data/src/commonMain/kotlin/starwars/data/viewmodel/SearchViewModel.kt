package starwars.data.viewmodel

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import starwars.data.models.BaseResource
import starwars.data.models.RootResource
import starwars.data.repo.StarWarsRepo
import starwars.data.state.ResourceResult

class SearchViewModel(private val starWarsRepo: StarWarsRepo): BaseViewModel() {

    private var job: Job? = null
    private var pageNumber = 0
    private var resourceName = String()
    private var resultsList = mutableListOf<Any>()

    private val _resourceResults = MutableStateFlow<ResourceResult<Any>>(ResourceResult.Empty)
    val resourceResults: StateFlow<ResourceResult<Any>> = _resourceResults

    private val _resourceItems = MutableStateFlow(emptyList<Any>())
    val resourceItems: StateFlow<List<Any>> = _resourceItems

    fun searchResources(resourceType: String, search: String, page: Int) {
        job = coroutineScope.launch {
            starWarsRepo.searchResources(resourceType, search, page).collect { results ->
                if (results is ResourceResult.Success) {

                    // If page <= pageNumber and resource type is not
                    // equal to cached type, clear cached resultsList
                    if (((page <= pageNumber) && (resourceType != resourceName)) || page == 1) {
                        resultsList = mutableListOf()
                    }

                    // Otherwise append results to cached resultsList
                    ((results.data as BaseResource<*>).results as MutableList<*>).forEach {
                        resultsList += it as Any
                    }
                    _resourceItems.value = resultsList.toList()
                    resourceName = resourceType
                    pageNumber = page

                }
                _resourceResults.value = results
            }
        }
    }

    fun dismissJob() {
        job?.cancel()
    }

    fun observeResourceResults(provideNewState: ((ResourceResult<Any>) -> Unit)) {
        _resourceResults.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }

    @Suppress("unused")
    fun observeResourceItems(provideNewState: ((List<Any>) -> Unit)) {
        _resourceItems.onEach {
            provideNewState.invoke(it)
        }.launchIn(coroutineScope)
    }
}