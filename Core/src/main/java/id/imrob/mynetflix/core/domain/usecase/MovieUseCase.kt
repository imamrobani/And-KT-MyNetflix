package id.imrob.mynetflix.core.domain.usecase

import id.imrob.mynetflix.core.data.MovieRepository

class MovieUseCase(
    private val movieRepository: MovieRepository
): IMovieInteractor {
    override suspend fun getNowPlayingMovie() = movieRepository.getNowPlayingMovie()
    override suspend fun getPopularMovie() = movieRepository.getPopularMovie()
    override suspend fun getUpcomingMovie() = movieRepository.getUpcomingMovie()

    override suspend fun getMovieDetail(id: String) = movieRepository.getMovieDetail(id)
}