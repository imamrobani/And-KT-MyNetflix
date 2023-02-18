package id.imrob.mynetflix.core.data

import id.imrob.mynetflix.core.data.local.LocalDataSource
import id.imrob.mynetflix.core.data.remote.RemoteDataSource
import id.imrob.mynetflix.core.data.remote.Resource
import id.imrob.mynetflix.core.domain.model.Movie
import id.imrob.mynetflix.core.domain.model.Videos
import id.imrob.mynetflix.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {
    override suspend fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> =
        remoteDataSource.getNowPlayingMovie()

    override suspend fun getPopularMovie(): Flow<Resource<List<Movie>>> =
        remoteDataSource.getPopularMovie()

    override suspend fun getUpcomingMovie(): Flow<Resource<List<Movie>>> =
        remoteDataSource.getUpcomingMovie()

    override suspend fun getMovieDetail(id: String): Flow<Movie> =
        remoteDataSource.getMovieDetail(id)

    override suspend fun getVideoFromMovie(id: String): Flow<Resource<Videos>> =
        remoteDataSource.getVideosFromMovie(id)


    override suspend fun getAllFavoriteMovie(): Flow<List<Movie>> =
        localDataSource.getAllFavoriteMovie()

    override suspend fun isMovieFavorite(id: String): Flow<Boolean> =
        localDataSource.isMMovieFavorite(id)

    override suspend fun addMovieToFavorite(movie: Movie): Flow<Boolean> =
        localDataSource.addFavoriteMovie(movie)

    override suspend fun removeMovieFromFavorite(movie: Movie): Flow<Boolean> =
        localDataSource.removeMovieFromFavorite(movie)
}