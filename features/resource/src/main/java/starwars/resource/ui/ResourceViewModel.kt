package starwars.resource.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import starwars.data.repository.Repository
import starwars.data.util.ContextProviders
import javax.inject.Inject

@HiltViewModel
class ResourceViewModel @Inject constructor(
    private val repository: Repository,
    private val contextProviders: ContextProviders
) : ViewModel() {


}