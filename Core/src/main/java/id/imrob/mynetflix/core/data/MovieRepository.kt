package id.imrob.mynetflix.core.data

import id.imrob.mynetflix.core.data.remote.RemoteDataSource
import id.imrob.mynetflix.core.domain.model.Movie
import id.imrob.mynetflix.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val remoteDataSource: RemoteDataSource
):IMovieRepository {
    override suspend fun getNowPlayingMovie(): Flow<List<Movie>> =
        remoteDataSource.getNowPlayingMovie()

    override suspend fun getMovieDetail(id: String): Flow<Movie> =
        remoteDataSource.getMovieDetail(id)
}