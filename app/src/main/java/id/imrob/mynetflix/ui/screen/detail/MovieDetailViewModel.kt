package id.imrob.mynetflix.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.imrob.mynetflix.MovieApplication
import id.imrob.mynetflix.core.data.MovieRepository
import id.imrob.mynetflix.core.domain.model.Movie
import id.imrob.mynetflix.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> get() = _movie

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite get() = _isFavorite

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
                MovieDetailViewModel(application.appMovieContainer.movieUseCase)
            }
        }
    }

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            movieUseCase.getMovieDetail(id).collect {
                _movie.value = it
            }
        }
    }

    fun isMovieFavorite(id: String) {
        viewModelScope.launch {
            movieUseCase.isMovieFavorite(id).collect {
                _isFavorite.value = it
            }
        }
    }

    fun addToFavorite(movie: Movie) {
        viewModelScope.launch {
            movieUseCase.addMovieToFavorite(movie).collect {
                //isMovieFavorite(movie.id)
            }
        }
    }

    fun removeFromFavorite(movie: Movie) {
        viewModelScope.launch {
            movieUseCase.removeMoveFromFavorite(movie).collect {
                //isMovieFavorite(movie.id)
            }
        }
    }
}