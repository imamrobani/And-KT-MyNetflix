package id.imrob.mynetflix.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.imrob.mynetflix.MovieApplication
import id.imrob.mynetflix.data.MovieDatasource
import id.imrob.mynetflix.data.MovieRepository
import id.imrob.mynetflix.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies: StateFlow<List<Movie>> get() = _movies
    private val currentMovie = mutableStateListOf<Movie>()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MovieApplication
                val respository = application.appMovieContainer.movieRepository
                MovieViewModel(respository)
            }
        }
    }

    fun getMovies() {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovie().collect{
                currentMovie.clear()
                currentMovie.addAll(it)
                _movies.value = currentMovie
            }
        }
    }

    fun searchMovie(keyword: String) {
        _movies.value = currentMovie.filter { movie ->
            movie.title.contains(keyword,true) || movie.description.contains(keyword,true)
        }
    }

}