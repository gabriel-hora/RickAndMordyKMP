package horatech.rickandmortykmm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import horatech.rickandmortykmm.presentation.navigation.RouteNavigation.HOME
import horatech.rickandmortykmm.presentation.navigation.RouteNavigation.SPLASH
import horatech.rickandmortykmm.presentation.screen.HomeScreen
import horatech.rickandmortykmm.presentation.screen.SplashScreen

@Composable
fun SetupNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SPLASH
    ) {
        composable(SPLASH) {
            SplashScreen(navController)
        }
        composable(HOME) {
            HomeScreen()
        }
    }
}