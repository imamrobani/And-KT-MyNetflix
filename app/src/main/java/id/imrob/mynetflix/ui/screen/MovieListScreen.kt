package id.imrob.mynetflix.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.imrob.mynetflix.data.MovieDatasource
import id.imrob.mynetflix.domain.model.Movie
import id.imrob.mynetflix.ui.component.MovieAppBar
import id.imrob.mynetflix.ui.component.MovieItem

@ExperimentalMaterial3Api
@Composable
fun MovieListScreen(paddingValues: PaddingValues, movies: List<Movie>) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues( vertical = 16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                isGrid = false,
                movie = movie,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}