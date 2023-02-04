package id.imrob.mynetflix.ui.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import id.imrob.mynetflix.core.data.MovieDatasource
import id.imrob.mynetflix.core.domain.model.Movie
import id.imrob.mynetflix.ui.Routers
import id.imrob.mynetflix.ui.component.MovieItem

@ExperimentalMaterial3Api
@Composable
fun MovieGridScreen(
    paddingValues: PaddingValues,
    movies: List<Movie>,
    navHostController: NavHostController
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(paddingValues),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues( vertical = 16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                isGrid = true,
                movie = movie,
                modifier = Modifier.padding(horizontal = 16.dp),
                onItemClick = { movie ->
                    navHostController.navigate("${Routers.DETAIL}/${movie.id}")
                }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
private fun PreviewMovieGridScreen() {
    MovieGridScreen(paddingValues = PaddingValues(), MovieDatasource.getNowPlayingMovie(), rememberNavController())
}


