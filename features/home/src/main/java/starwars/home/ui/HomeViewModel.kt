package starwars.home.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import starwars.data.api.response.Resource
import starwars.data.api.response.Status
import starwars.data.model.Item
import starwars.data.repository.Repository
import starwars.data.util.ContextProviders
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val contextProviders: ContextProviders
) : ViewModel() {

    private val _rootItems = MutableLiveData<Resource<List<Item>>>()
    val rootItems: LiveData<Resource<List<Item>>> = _rootItems

    fun getRootItems() {
        viewModelScope.launch(contextProviders.IO) {
            if(!_rootItems.value?.data.isNullOrEmpty())
                return@launch
            _rootItems.postValue(Resource.loading(null))
            val apiResponse = repository.getRootData()
            when (apiResponse.status) {
                Status.SUCCESS -> {
                    _rootItems.postValue(Resource.success(apiResponse.data))
                }
                Status.ERROR -> {
                    _rootItems.postValue(Resource.error(apiResponse.message.toString(), null))
                }
                else -> {}
            }
        }
    }

}