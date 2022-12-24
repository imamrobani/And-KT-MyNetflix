package id.imrob.mynetflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.imrob.mynetflix.data.MovieDatasource
import id.imrob.mynetflix.domain.model.Movie
import id.imrob.mynetflix.ui.component.MovieAppBar
import id.imrob.mynetflix.ui.component.MovieItem
import id.imrob.mynetflix.ui.screen.MovieGridScreen
import id.imrob.mynetflix.ui.screen.MovieListScreen
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
fun NetflixCloneApp() {

    val movies: List<Movie> by rememberSaveable() {
        mutableStateOf(MovieDatasource.getNowPlayingMovie())
    }

    var isGrid by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MovieAppBar(onViewChange = { isGrid = it }) }
    ) { contentPadding ->
        if (isGrid) MovieGridScreen(contentPadding, movies)
        else MovieListScreen(contentPadding, movies)
    }
}
