package horatech.rickandmortykmm.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import horatech.rickandmortykmm.data.model.Character
import horatech.rickandmortykmm.presentation.viewmodel.HomeViewModel
import horatech.rickandmortykmm.theme.BackgroundApp
import horatech.rickandmortykmm.theme.BackgroundTextCard
import horatech.rickandmortykmm.theme.MontserratFamily

@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = viewModel { HomeViewModel() }
    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCharacters()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Rick and Morty KMP",
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
        Column(
            modifier = Modifier.fillMaxSize().background(color = BackgroundApp)
        ) {
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
    ) {
        when {
            isLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeCap = StrokeCap.Round,
                        strokeWidth = 5.dp
                    )
                }
            }

            isError -> {
                Text("Error: $messageError")
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Define duas colunas na grade
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listCharacter.size) { item ->
                        ItemCharacter(character = listCharacter[item])
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCharacter(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        backgroundColor = Color.LightGray,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CoilImage(
                imageModel = { character.image },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundTextCard)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = character.name,
                    fontFamily = MontserratFamily(),
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
            }
        }
    }
}