package horatech.rickandmortykmm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import horatech.rickandmortykmm.data.repository.ApiRepository
import horatech.rickandmortykmm.data.repository.ApiRepositoryImpl
import horatech.rickandmortykmm.presentation.navigation.ListUiState
import horatech.rickandmortykmm.util.launchWithState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val repository: ApiRepository = ApiRepositoryImpl()

    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState

    private var requestJob: Job = Job()

    fun getCharacters() {
        viewModelScope.launchWithState(
            job = requestJob,
            block = {
                repository.getCharacter()
            },
            onLoading = {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        hasError = false
                    )
                }
            },
            onSuccess = { response ->
                _uiState.update {
                    it.copy(
                        characters = response,
                        isLoading = false,
                        hasError = false
                    )
                }
            },
            onError = { responseError ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hasError = true,
                        messageError = responseError.message ?: "Error null"
                    )
                }
            }
        )
    }
}