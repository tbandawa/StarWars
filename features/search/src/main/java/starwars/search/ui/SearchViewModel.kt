package starwars.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.tbandawa.android.commons.extensions.getPageNumber
import starwars.data.api.response.Resource
import starwars.data.model.BaseResult
import starwars.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _resourceItems = MutableLiveData<Resource<BaseResult<Any>>>()
    val resourceItems: LiveData<Resource<BaseResult<Any>>> = _resourceItems

    @InternalCoroutinesApi
    fun searchResource(resourceType: String, searchQuery: String) {
        viewModelScope.launch {
            repository.searchResource(resourceType, searchQuery).collect { value ->
                _resourceItems.postValue(value)
            }
        }
    }

    @InternalCoroutinesApi
    fun getResourcesByPage(resourceType: String, url: String) {
        val page = url.getPageNumber()
        viewModelScope.launch {
            repository.getResourcesByPage(resourceType, page).collect { value ->
                _resourceItems.postValue(value)
            }
        }
    }

}