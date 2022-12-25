package starwars.data.viewmodel

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val coroutineScope: CoroutineScope
    fun dispose()
    protected open fun onCleared()
}