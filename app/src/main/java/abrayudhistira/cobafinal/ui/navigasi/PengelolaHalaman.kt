package abrayudhistira.cobafinal.ui.navigasi

import abrayudhistira.cobafinal.ui.HomeApp
import abrayudhistira.cobafinal.ui.jenisproperti.view.HomeJenisPropertiView
import abrayudhistira.cobafinal.ui.manajerproperti.view.HomeManajerPropertiView
import abrayudhistira.cobafinal.ui.pemilik.view.HomePemilikView
import abrayudhistira.cobafinal.ui.pemilik.view.InsertViewPemilik
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
                    onHalamanJenisProperti = {
                        navController.navigate(DestinasiHomeJenisProperty.route)
                    },
                    onHalamanPemilik = {
                        navController.navigate(DestinasiHomePemilik.route)
                    },
                    onHalamanManajerProperti = {
                        navController.navigate(DestinasiManajerProperty.route)
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
            composable(route = DestinasiHomeJenisProperty.route) {
                HomeJenisPropertiView(
                    navController = navController,
                    navigateToItemEntry = {navController.navigate(DestinasiEntry.route)}
                )
            }
            composable(route = DestinasiHomePemilik.route) {
                HomePemilikView(
                    navController = navController,
                    navigateToItemEntry = {navController.navigate(DestinasiEntryPemilik.route)}
                )
            }
            composable(route = DestinasiEntryPemilik.route) {
                InsertViewPemilik( navigateBack = {
                    navController.navigate(DestinasiHomePemilik.route){
                        popUpTo(DestinasiHomePemilik.route){
                            inclusive = true
                        }
                    }
                })
            }
            composable(route = DestinasiManajerProperty.route) {
                HomeManajerPropertiView(
                    navController = navController,
                    navigateToItemEntry = {navController.navigate(DestinasiEntry.route)}
                )
            }
        }
    }
}