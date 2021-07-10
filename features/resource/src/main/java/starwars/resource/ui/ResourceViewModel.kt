package starwars.resource.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.tbandawa.android.commons.extensions.getResourceId
import me.tbandawa.android.commons.extensions.getResourceType
import starwars.data.api.response.Resource
import starwars.data.api.response.Status
import starwars.data.repository.Repository
import starwars.data.util.ContextProviders
import javax.inject.Inject

@HiltViewModel
class ResourceViewModel @Inject constructor(
    private val repository: Repository,
    private val contextProviders: ContextProviders
) : ViewModel() {

    private val _resource = MutableLiveData<Resource<Any>>()
    val resource: LiveData<Resource<Any>> = _resource

    fun getResource(resourceType: String, resourceId: Int) {
        viewModelScope.launch(contextProviders.IO) {
            _resource.postValue(Resource.loading(null))
            val apiResponse = repository.getResource(resourceType, resourceId)
            when (apiResponse.status) {
                Status.SUCCESS -> {
                    _resource.postValue(Resource.success(apiResponse.data))
                }
                Status.ERROR -> {
                    _resource.postValue(Resource.error(apiResponse.message.toString(), null))
                }
                else -> {}
            }
        }
    }

    fun getResourceTitle(resourceUrl: String, callback: (Any) -> Unit) {
        val resourceId = resourceUrl.getResourceId()
        val resourceType = resourceUrl.getResourceType()
        viewModelScope.launch(contextProviders.IO) {
            val apiResponse = repository.getResource(resourceType, resourceId)
            when (apiResponse.status) {
                Status.SUCCESS -> {
                    apiResponse.data?.let { any ->
                        callback.invoke(any)
                    }
                }
            }
        }
    }

}