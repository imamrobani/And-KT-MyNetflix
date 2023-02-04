package id.imrob.mynetflix.ui.screen.dashboard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.imrob.mynetflix.ui.Routers
import id.imrob.mynetflix.ui.screen.dashboard.favorite.FavoriteScreen
import id.imrob.mynetflix.ui.screen.dashboard.home.HomeScreen
import id.imrob.mynetflix.ui.screen.dashboard.profile.ProfileScreen

@ExperimentalMaterial3Api
@Composable
fun DashboardScreenController(
    parentNavHostController: NavHostController,
    dashboardNavController: NavHostController
){
    NavHost(navController = dashboardNavController, startDestination = Routers.HOME){
        composable(
            route = Routers.HOME
        ) {
            HomeScreen(parentNavHostController)
        }

        composable(
            route = Routers.FAVORITE
        ) {
            FavoriteScreen(parentNavHostController)
        }

        composable(
            route = Routers.PROFILE
        ) {
            ProfileScreen(parentNavHostController)
        }
    }
}