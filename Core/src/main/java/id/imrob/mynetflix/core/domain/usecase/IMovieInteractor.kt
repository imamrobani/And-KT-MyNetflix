package id.imrob.mynetflix.core.domain.usecase

import id.imrob.mynetflix.core.data.remote.Resource
import id.imrob.mynetflix.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieInteractor {
    suspend fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>
    suspend fun getPopularMovie(): Flow<Resource<List<Movie>>>
    suspend fun getUpcomingMovie(): Flow<Resource<List<Movie>>>
    suspend fun getMovieDetail(id: String): Flow<Movie>
}