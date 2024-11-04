package horatech.rickandmortykmm.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> CoroutineScope.launchWithState(
    job: Job = Job(),
    block: suspend () -> T,
    onLoading: () -> Unit,
    onSuccess: (T) -> Unit,
    onError: (Exception) -> Unit
) {
    this.launch(job) {
        onLoading.invoke()
        try {
            val response = block.invoke()
            delay(2000L)
            onSuccess.invoke(response)
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }
}