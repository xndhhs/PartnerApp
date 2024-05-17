package ro.partnerapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.getViewModel
import ro.partnerapp.ui.partner_app.details_screen.PartnerDetailsScreen
import ro.partnerapp.ui.partner_app.details_screen.PartnerDetailsViewModel
import ro.partnerapp.ui.partner_app.main_screen.PartnerAppMainScreen
import ro.partnerapp.ui.partner_app.main_screen.PartnerAppMainScreenViewModel
import ro.partnerapp.ui.theme.PartnerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PartnerAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PartnerAppContent()
                }
            }
        }
    }
}

@Composable
fun PartnerAppContent() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = "partner_app_main_screen") {
        composable("partner_app_main_screen") {
            val viewModel = getViewModel<PartnerAppMainScreenViewModel>()
            PartnerAppMainScreen(viewModel = viewModel,
                onTryAgain = { viewModel.getPartnerData() },
                onPartnerSelected = {
                    viewModel.updateSelectedPartner(it)
                    navigationController.navigate("destination_partner_details/${it.id}")
                }
            )
        }
        composable(
            route = "destination_partner_details/{partner_id}",
            arguments = listOf(navArgument("partner_id") {
                type = NavType.IntType
            })
        ) {
            val viewModel = getViewModel<PartnerDetailsViewModel>()
            PartnerDetailsScreen(viewModel)
        }
    }
}