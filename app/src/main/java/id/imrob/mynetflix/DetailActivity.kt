package id.imrob.mynetflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import id.imrob.mynetflix.domain.model.Movie
import id.imrob.mynetflix.ui.screen.MovieDetailScreen
import id.imrob.mynetflix.ui.theme.MyNetflixTheme

@ExperimentalMaterial3Api
class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.getParcelable<Movie>(EXTRA_MOVIE)?.let { movie ->
            setContent {
                MyNetflixTheme {
                    MovieDetailScreen(movie = movie) {
                        finish()
                    }
                }
            }
        }
    }

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }
}

