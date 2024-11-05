package horatech.rickandmortykmm.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import horatech.rickandmortykmm.presentation.navigation.RouteNavigation.HOME
import horatech.rickandmortykmm.theme.BackgroundApp
import horatech.rickandmortykmm.theme.MontserratFamily
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import rickandmortykmm.composeapp.generated.resources.Res
import rickandmortykmm.composeapp.generated.resources.icon_splash_rm

@Composable
fun SplashScreen(navController: NavController) {

    var rotateLeft by remember { mutableStateOf(true) }
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate("home")
    }

    val rotationAngle by animateFloatAsState(
        targetValue = if (rotateLeft) -10f else 10f,
        animationSpec = tween(durationMillis = 1500)
    )

    LaunchedEffect(rotationAngle) {
        delay(500)
        rotateLeft = !rotateLeft
    }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(1000)) + scaleIn(
                initialScale = 0.5f,
                animationSpec = tween(1000)
            )
        ) {
            // Imagem com efeito de rotação suave
            Image(
                painter = painterResource(Res.drawable.icon_splash_rm),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(300.dp)
                    .graphicsLayer(rotationZ = rotationAngle)
            )
        }
    }
}