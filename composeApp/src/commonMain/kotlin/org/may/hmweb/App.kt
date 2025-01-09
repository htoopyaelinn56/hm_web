package org.may.hmweb

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) MyTheme.DarkColorScheme else MyTheme.LightColorScheme,
    ) {
        val navController = rememberNavController()
        val viewModel = viewModel {
            CommonViewModel()
        }

        NavHost(navController = navController, startDestination = Routes.Home.name) {
            composable(route = Routes.Home.name) {
                HomePage(onTapToDetail = {
                    viewModel.setSelectedItem(it)
                    navController.navigate(Routes.Detail.name)
                })
            }

            composable(
                route = Routes.Detail.name,
            ) {
                val itemData = viewModel.selectedItem.collectAsState().value
                DetailPage(
                    itemData = itemData,
                    onPop = { navController.popBackStack() }
                )
            }
        }



    }
}
