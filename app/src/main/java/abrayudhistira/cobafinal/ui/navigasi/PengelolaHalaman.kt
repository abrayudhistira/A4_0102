package abrayudhistira.cobafinal.ui.navigasi

import abrayudhistira.cobafinal.ui.HomeApp
import abrayudhistira.cobafinal.ui.jenisproperti.view.DestinasiUpdateJenisProperti
import abrayudhistira.cobafinal.ui.jenisproperti.view.DetailJenisPropertiView
import abrayudhistira.cobafinal.ui.jenisproperti.view.HomeJenisPropertiView
import abrayudhistira.cobafinal.ui.jenisproperti.view.InsertViewJenisProperty
import abrayudhistira.cobafinal.ui.jenisproperti.view.UpdateJenisPropertiView
import abrayudhistira.cobafinal.ui.manajerproperti.view.HomeManajerPropertiView
import abrayudhistira.cobafinal.ui.pemilik.view.DetailPemilikView
import abrayudhistira.cobafinal.ui.pemilik.view.HomePemilikView
import abrayudhistira.cobafinal.ui.pemilik.view.InsertViewPemilik
import abrayudhistira.cobafinal.ui.pemilik.view.UpdatePemilikView
import abrayudhistira.cobafinal.ui.property.HomePropertyView
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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
                        navController.navigate(DestinasiHomeManajerProperty.route)
                    },
                    modifier = modifier
                )
            }
            composable(route = DestinasiHomeProperty.route) {
                HomePropertyView(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiHomeProperty.route) }
                )
            }
            composable(route = DestinasiHomeJenisProperty.route) {
                HomeJenisPropertiView(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiInsertJenisProperty.route) },
                    onDetailClick = { idJenis ->
                        navController.navigate("${DestinasiUpdateJenisProperti.route}/$idJenis")
                    }
                )
            }
            composable(route = DestinasiInsertJenisProperty.route) {
                InsertViewJenisProperty(navigateBack = {
                    navController.navigate(DestinasiHomeJenisProperty.route) {
                        popUpTo(DestinasiHomeJenisProperty.route) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(
                route = DestinasiDetailJenisProperti.routewithArgument,
                arguments = listOf(
                    navArgument(DestinasiDetailJenisProperti.idJenisArg) {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val idJenis = backStackEntry.arguments?.getInt(DestinasiDetailJenisProperti.idJenisArg)
                println("DetailJenisProperty called with ID: $idJenis")
                idJenis?.let { id ->
                    DetailJenisPropertiView(
                        navigateBack = {
                            navController.navigate(DestinasiHomeJenisProperty.route) {
                                popUpTo(DestinasiHomeJenisProperty.route) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToEdit = {
                            navController.navigate("${DestinasiUpdatePemilik.route}/$idJenis")
                        }
                    )
                }
            }
            composable(
                route = DestinasiUpdateJenisProperti.routewithArgument,
                arguments = listOf(
                    navArgument(DestinasiUpdateJenisProperti.idJenisArg) {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val idJenis = backStackEntry.arguments?.getInt(DestinasiUpdateJenisProperti.idJenisArg)
                idJenis?.let { id ->
                    UpdateJenisPropertiView(
                        onBack = { navController.popBackStack() },
                        onNavigate = { navController.popBackStack() }
                    )
                }
            }
            composable(route = DestinasiHomePemilik.route) {
                HomePemilikView(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryPemilik.route) },
                    onDetailClick = { idPemilik ->
                        navController.navigate("${DestinasiDetailPemilik.route}/$idPemilik")
                    }
                )
            }
            composable(route = DestinasiEntryPemilik.route) {
                InsertViewPemilik(navigateBack = {
                    navController.navigate(DestinasiHomePemilik.route) {
                        popUpTo(DestinasiHomePemilik.route) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(
                route = DestinasiDetailPemilik.routewithArgument,
                arguments = listOf(
                    navArgument(DestinasiDetailPemilik.idPemilikArg) {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val idPemilik = backStackEntry.arguments?.getInt(DestinasiDetailPemilik.idPemilikArg)
                println("DetailPemilikView called with ID: $idPemilik")
                idPemilik?.let { id ->
                    DetailPemilikView(
                        navigateBack = {
                            navController.navigate(DestinasiHomePemilik.route) {
                                popUpTo(DestinasiHomePemilik.route) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToEdit = {
                            navController.navigate("${DestinasiUpdatePemilik.route}/$idPemilik")
                        }
                    )
                }
            }
            composable(
                route = DestinasiUpdatePemilik.routewithArgument,
                arguments = listOf(
                    navArgument(DestinasiUpdatePemilik.idPemilikArg) {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val idPemilik = backStackEntry.arguments?.getInt(DestinasiUpdatePemilik.idPemilikArg)
                idPemilik?.let { id ->
                    UpdatePemilikView(
                        onBack = { navController.popBackStack() },
                        onNavigate = { navController.popBackStack() }
                    )
                }
            }
            composable(route = DestinasiHomeManajerProperty.route) {
                HomeManajerPropertiView(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiHomeManajerProperty.route) }
                )
            }
        }
    }
}