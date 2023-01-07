package id.imrob.mynetflix.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.imrob.mynetflix.ui.MovieViewModel
import id.imrob.mynetflix.ui.component.MovieAppBar
import id.imrob.mynetflix.ui.component.MovieSearchField
import id.imrob.mynetflix.ui.screen.list.MovieGridScreen
import id.imrob.mynetflix.ui.screen.list.MovieListScreen

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: MovieViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = MovieViewModel.Factory)
) {
    val movies by viewModel.movies.collectAsState(arrayListOf())
    var isGrid by remember { mutableStateOf(false) }
    var keyword by remember { mutableStateOf("") }

    LaunchedEffect(""){ viewModel.getMovies() }
    LaunchedEffect(keyword){ viewModel.searchMovie(keyword) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                MovieAppBar(onViewChange = { isGrid = it })
                MovieSearchField(
                    keyword,
                    onTextChange = {
                        keyword = it
                    },
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }
    ) { contentPadding ->
        if (isGrid) MovieGridScreen(contentPadding, movies, navHostController)
        else MovieListScreen(contentPadding, movies, navHostController)
    }
}