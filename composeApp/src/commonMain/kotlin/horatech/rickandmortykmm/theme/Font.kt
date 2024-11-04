package horatech.rickandmortykmm.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import rickandmortykmm.composeapp.generated.resources.Res
import rickandmortykmm.composeapp.generated.resources.montserrat_black
import rickandmortykmm.composeapp.generated.resources.montserrat_bold
import rickandmortykmm.composeapp.generated.resources.montserrat_medium
import rickandmortykmm.composeapp.generated.resources.montserrat_regular
import rickandmortykmm.composeapp.generated.resources.montserrat_semibold
import rickandmortykmm.composeapp.generated.resources.montserrat_thin

@Composable
fun MontserratFamily() = FontFamily(
    Font(Res.font.montserrat_thin, weight = FontWeight.Thin),
    Font(Res.font.montserrat_bold, weight = FontWeight.Bold),
    Font(Res.font.montserrat_black, weight = FontWeight.Black),
    Font(Res.font.montserrat_regular, weight = FontWeight.Normal),
    Font(Res.font.montserrat_semibold, weight = FontWeight.SemiBold),
    Font(Res.font.montserrat_medium, weight = FontWeight.Medium),
)