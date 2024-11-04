package horatech.rickandmortykmm.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import horatech.rickandmortykmm.data.model.Character
import horatech.rickandmortykmm.presentation.viewmodel.HomeViewModel
import horatech.rickandmortykmm.theme.BackgroundApp
import horatech.rickandmortykmm.theme.MontserratFamily

@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = viewModel { HomeViewModel() }
    val state = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Rick and Morty KMM",
                        color = Color.White,
                        fontFamily = MontserratFamily(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                backgroundColor = BackgroundApp
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = {
                    viewModel.getCharacters()
                }
            ) {
                Text("Click me")
            }

            ListCharacter(
                isLoading = state.value.isLoading,
                isError = state.value.hasError,
                messageError = state.value.messageError,
                listCharacter = state.value.characters
            )
        }
    }
}

@Composable
fun ListCharacter(
    isLoading: Boolean,
    isError: Boolean,
    messageError: String,
    listCharacter: List<Character>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            isLoading -> {
                Text("Carregando..")
            }

            isError -> {
                Text("Error: $messageError")
            }

            else -> {
                listCharacter.forEach {
                    Text(it.name)
                }
            }
        }
    }
}