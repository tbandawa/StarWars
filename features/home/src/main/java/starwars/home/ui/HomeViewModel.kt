package starwars.home.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import starwars.data.api.response.Resource
import starwars.data.model.Item
import starwars.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _rootItems = MutableLiveData<Resource<List<Item>>>()
    val rootItems: LiveData<Resource<List<Item>>> = _rootItems

    @InternalCoroutinesApi
    fun getRootItems() {
        viewModelScope.launch {
            if(_rootItems.value != null)
                return@launch
            repository.getRootData().collect { value ->
                _rootItems.postValue(value)
            }
        }
    }

}