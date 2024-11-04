package horatech.rickandmortykmm

import androidx.compose.ui.window.ComposeUIViewController
import horatech.rickandmortykmm.presentation.navigation.SetupNavigation

fun MainViewController() = ComposeUIViewController {
    SetupNavigation()
}