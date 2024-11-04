package horatech.rickandmortykmm.presentation.navigation

import horatech.rickandmortykmm.data.model.Character

data class ListUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val hasError: Boolean = false,
    val messageError: String = ""
)