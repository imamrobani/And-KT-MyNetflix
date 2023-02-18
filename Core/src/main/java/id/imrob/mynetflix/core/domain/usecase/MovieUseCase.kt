package id.imrob.mynetflix.core.domain.usecase

import id.imrob.mynetflix.core.data.MovieRepository
import id.imrob.mynetflix.core.data.remote.Resource
import id.imrob.mynetflix.core.domain.model.Movie
import id.imrob.mynetflix.core.domain.model.Videos
import kotlinx.coroutines.flow.Flow

class MovieUseCase(
    private val movieRepository: MovieRepository
): IMovieInteractor {
    override suspend fun getNowPlayingMovie() = movieRepository.getNowPlayingMovie()
    override suspend fun getPopularMovie() = movieRepository.getPopularMovie()
    override suspend fun getUpcomingMovie() = movieRepository.getUpcomingMovie()
    override suspend fun getMovieDetail(id: String) = movieRepository.getMovieDetail(id)
    override suspend fun getVideoFromMovie(id: String): Flow<Resource<Videos>> = movieRepository.getVideoFromMovie(id)
    override suspend fun getAllFavoriteMovie() = movieRepository.getAllFavoriteMovie()
    override suspend fun isMovieFavorite(id: String) = movieRepository.isMovieFavorite(id)

    override suspend fun addMovieToFavorite(movie: Movie) = movieRepository.addMovieToFavorite(movie)
    override suspend fun removeMoveFromFavorite(movie: Movie) = movieRepository.removeMovieFromFavorite(movie)
}