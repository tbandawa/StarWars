package starwars.resources.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.tbandawa.android.commons.extensions.getPageNumber
import starwars.data.api.response.Resource
import starwars.data.api.response.Status
import starwars.data.model.BaseResult
import starwars.data.repository.Repository
import starwars.data.util.ContextProviders
import javax.inject.Inject

@HiltViewModel
class ResourcesViewModel @Inject constructor(
    private val repository: Repository,
    private val contextProviders: ContextProviders
) : ViewModel() {

    private val _resourceItems = MutableLiveData<Resource<BaseResult<Any>>>()
    val resourceItems: LiveData<Resource<BaseResult<Any>>> = _resourceItems

    fun getResources(resourceType: String) {
        viewModelScope.launch(contextProviders.IO) {
            _resourceItems.postValue(Resource.loading(null))
            val apiResponse = repository.getResources(resourceType)
            when (apiResponse.status) {
                Status.SUCCESS -> {
                    _resourceItems.postValue(Resource.success(apiResponse.data))
                }
                Status.ERROR -> {
                    _resourceItems.postValue(Resource.error(apiResponse.message.toString(), null))
                }
                else -> {}
            }
        }
    }

    fun getResourcesByPage(resourceType: String, url: String) {
        val page = url.getPageNumber()
        viewModelScope.launch(contextProviders.IO) {
            _resourceItems.postValue(Resource.loading(null))
            val apiResponse = repository.getResourcesByPage(resourceType, page)
            when (apiResponse.status) {
                Status.SUCCESS -> {
                    _resourceItems.postValue(Resource.success(apiResponse.data))
                }
                Status.ERROR -> {
                    _resourceItems.postValue(Resource.error(apiResponse.message.toString(), null))
                }
                else -> {}
            }
        }
    }

}