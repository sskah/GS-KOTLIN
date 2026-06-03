package br.com.fiap.coldorbitmedtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.coldorbitmedtrack.navigation.AppRoutes
import br.com.fiap.coldorbitmedtrack.repository.getMockAlerts
import br.com.fiap.coldorbitmedtrack.repository.getMockBatches
import br.com.fiap.coldorbitmedtrack.screens.AlertsScreen
import br.com.fiap.coldorbitmedtrack.screens.BatchDetailScreen
import br.com.fiap.coldorbitmedtrack.screens.DeliveriesScreen
import br.com.fiap.coldorbitmedtrack.screens.HomeScreen
import br.com.fiap.coldorbitmedtrack.screens.ProfileScreen
import br.com.fiap.coldorbitmedtrack.screens.UpdateStatusScreen
import br.com.fiap.coldorbitmedtrack.ui.theme.ColdOrbitMedTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColdOrbitMedTrackTheme {
                val navController = rememberNavController()
                val batches = remember { mutableStateListOf(*getMockBatches().toTypedArray()) }
                val alerts = remember { mutableStateListOf(*getMockAlerts().toTypedArray()) }

                NavHost(
                    navController = navController,
                    startDestination = AppRoutes.HOME
                ) {
                    composable(AppRoutes.HOME) {
                        HomeScreen(navController = navController, batches = batches)
                    }

                    composable(AppRoutes.DELIVERIES) {
                        DeliveriesScreen(navController = navController, batches = batches)
                    }

                    composable(
                        route = AppRoutes.BATCH_DETAIL,
                        arguments = listOf(navArgument("batchId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val batchId = backStackEntry.arguments?.getString("batchId")
                        val batch = batches.find { it.id == batchId }
                        BatchDetailScreen(navController = navController, batch = batch)
                    }

                    composable(
                        route = AppRoutes.UPDATE_STATUS,
                        arguments = listOf(navArgument("batchId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val batchId = backStackEntry.arguments?.getString("batchId")
                        val batch = batches.find { it.id == batchId }
                        UpdateStatusScreen(
                            navController = navController,
                            batch = batch,
                            onSave = { newStatus, newTemperature, newObservation ->
                                val index = batches.indexOfFirst { it.id == batchId }
                                if (index >= 0) {
                                    batches[index] = batches[index].copy(
                                        status = newStatus,
                                        temperature = newTemperature,
                                        observation = newObservation
                                    )
                                }
                            }
                        )
                    }

                    composable(AppRoutes.PROFILE) {
                        ProfileScreen(navController = navController)
                    }

                    composable(AppRoutes.ALERTS) {
                        AlertsScreen(
                            navController = navController,
                            alerts = alerts,
                            onAcknowledge = { alertId ->
                                val index = alerts.indexOfFirst { it.id == alertId }
                                if (index >= 0) {
                                    alerts[index] = alerts[index].copy(acknowledged = true)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
