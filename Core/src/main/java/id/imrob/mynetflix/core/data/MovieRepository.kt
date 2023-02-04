package id.imrob.mynetflix.core.data

import id.imrob.mynetflix.core.data.remote.RemoteDataSource
import id.imrob.mynetflix.core.data.remote.Resource
import id.imrob.mynetflix.core.domain.model.Movie
import id.imrob.mynetflix.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {
    override suspend fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> =
        remoteDataSource.getNowPlayingMovie()

    override suspend fun getPopularMovie(): Flow<Resource<List<Movie>>> =
        remoteDataSource.getPopularMovie()

    override suspend fun getUpcomingMovie(): Flow<Resource<List<Movie>>> =
        remoteDataSource.getUpcomingMovie()

    override suspend fun getMovieDetail(id: String): Flow<Movie> =
        remoteDataSource.getMovieDetail(id)
}