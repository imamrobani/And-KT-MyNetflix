package id.imrob.mynetflix.ui.screen.detail

import id.imrob.mynetflix.core.domain.model.Movie
import id.imrob.mynetflix.core.domain.model.Videos

sealed class MovieDetailScreenState {
    class Success(
        val movieDetail: Movie,
        val videos: Videos
    ) : MovieDetailScreenState()

    class Error(
        val message: String,
        val whatError: MovieDetailScreenRequestEnum
    ) : MovieDetailScreenState()

    object Loading : MovieDetailScreenState()
    object Empty: MovieDetailScreenState()
}