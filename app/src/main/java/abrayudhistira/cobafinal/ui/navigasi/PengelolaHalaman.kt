package abrayudhistira.cobafinal.ui.navigasi

import abrayudhistira.cobafinal.ui.HomeApp
import abrayudhistira.cobafinal.ui.property.HomePropertyView
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = DestinasiMainScreen.route,
            modifier = Modifier.padding(it),
        ) {
            composable(route = DestinasiMainScreen.route) {
                HomeApp(
                    onHalamanProperti = {
                        navController.navigate(DestinasiHomeProperty.route)
                    },
                    modifier = modifier
                )
            }
            composable(route = DestinasiHomeProperty.route) {
                HomePropertyView(
                    navController = navController,
                    navigateToItemEntry = {navController.navigate(DestinasiEntry.route)}
                )
            }
        }
    }
}