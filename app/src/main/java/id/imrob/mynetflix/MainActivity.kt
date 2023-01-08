package id.imrob.mynetflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.imrob.mynetflix.ui.MainViewModel
import id.imrob.mynetflix.ui.Routers
import id.imrob.mynetflix.ui.screen.auth.login.LoginScreen
import id.imrob.mynetflix.ui.screen.auth.register.RegisterScreen
import id.imrob.mynetflix.ui.screen.detail.MovieDetailScreen
import id.imrob.mynetflix.ui.screen.home.HomeScreen
import id.imrob.mynetflix.ui.theme.MyNetflixTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNetflixTheme {
                NetflixCloneApp()
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun NetflixCloneApp(
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = MainViewModel.Factory )
) {

    val navController = rememberNavController()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getIsLoggedInUser()
    }
    
    isLoggedIn?.let {
        NavHost(navController = navController, startDestination = if(it) Routers.HOME else Routers.LOGIN){
            composable(route = Routers.LOGIN){
                LoginScreen(navController)
            }

            composable(route = Routers.REGISTER){
                RegisterScreen(navController)
            }

            composable(route = Routers.HOME) {
                HomeScreen(navController)
            }
            composable(
                route = "${Routers.DETAIL}/{movieId}",
                arguments = listOf(navArgument("movieId") {type = NavType.StringType})
            ) { NavBackStackEntry ->
                val movieId = NavBackStackEntry.arguments?.getString("movieId")
                MovieDetailScreen(movieId.orEmpty(), navController)
            }
        }
    }
}
