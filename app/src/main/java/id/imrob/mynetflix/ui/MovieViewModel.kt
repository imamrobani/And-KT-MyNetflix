package id.imrob.mynetflix.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.imrob.mynetflix.MovieApplication
import id.imrob.mynetflix.core.domain.usecase.MovieUseCase
import id.imrob.mynetflix.core.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel constructor(
    private val movieRepository: MovieUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies: StateFlow<List<Movie>> get() = _movies
    private val currentMovie = mutableStateListOf<Movie>()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MovieApplication
                MovieViewModel(application.appMovieContainer.movieUseCase)
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