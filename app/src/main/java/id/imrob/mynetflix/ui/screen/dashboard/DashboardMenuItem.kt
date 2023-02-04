package id.imrob.mynetflix.ui.screen.dashboard

import androidx.annotation.DrawableRes
import id.imrob.mynetflix.R
import id.imrob.mynetflix.ui.Routers

sealed class DashboardMenuItem(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    object Home: DashboardMenuItem(Routers.HOME, "Home", R.drawable.ic_home)
    object Favorite: DashboardMenuItem(Routers.FAVORITE, "Home", R.drawable.ic_favorite)
    object Profile: DashboardMenuItem(Routers.PROFILE, "Home", R.drawable.ic_profile)

    object Menus {
        val menus = listOf(
            Home, Favorite, Profile
        )
    }
}
