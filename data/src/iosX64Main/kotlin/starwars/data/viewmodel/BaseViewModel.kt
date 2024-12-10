package starwars.data.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual open class BaseViewModel {

    actual val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    actual fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    protected actual open fun onCleared() {
    }
}