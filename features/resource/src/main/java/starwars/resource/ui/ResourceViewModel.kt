package starwars.resource.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.tbandawa.android.commons.extensions.getResourceId
import me.tbandawa.android.commons.extensions.getResourceType
import starwars.data.api.response.Resource
import starwars.data.api.response.Status
import starwars.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class ResourceViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val resourcesMap = HashMap<String, Any>()

    private val _resource = MutableLiveData<Resource<Any>>()
    val resource: LiveData<Resource<Any>> = _resource

    @InternalCoroutinesApi
    fun getResource(resourceType: String, resourceId: Int) {
        viewModelScope.launch {
            if(_resource.value != null)
                return@launch
            repository.getResource(resourceType, resourceId).collect { value ->
                _resource.postValue(value)
            }
        }
    }

    @InternalCoroutinesApi
    fun getResourceTitle(resourceUrl: String, callback: (Any) -> Unit) {
        if (resourcesMap.containsKey(resourceUrl))
            return
        val resourceId = resourceUrl.getResourceId()
        val resourceType = resourceUrl.getResourceType()
        viewModelScope.launch {
            repository.getResource(resourceType, resourceId).collect { value ->
                if (value.status == Status.SUCCESS) {
                    value.data?.let { any ->
                        resourcesMap[resourceUrl] = any
                        callback.invoke(any)
                    }
                }
            }
        }
    }

}