package id.imrob.mynetflix.core.data.remote

import android.util.Log
import id.imrob.mynetflix.core.data.remote.network.MovieService
import id.imrob.mynetflix.core.data.remote.request.LoginRequest
import id.imrob.mynetflix.core.data.remote.request.RegisterRequest
import id.imrob.mynetflix.core.data.remote.response.toListMovie
import id.imrob.mynetflix.core.data.remote.response.toMovie
import id.imrob.mynetflix.core.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class RemoteDataSource(
    private val movieService: MovieService
) : SafeApiCall {
    suspend fun getNowPlayingMovie() = flow<List<Movie>> {
        movieService.getNowPlaying().toListMovie().let { emit(it) }
    }.catch {
        Log.d("RemoteDataSource", "getNowPlayingMovie: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun getMovieDetail(id: String) = flow {
        movieService.getMovieDetail(id).toMovie().let { emit(it) }
    }.catch {
        Log.d("MovieRepository", "getMovieDetail: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun register(registerRequest: RegisterRequest) = flow {
        emit(safeApiCall { movieService.register(registerRequest) })
    }

    suspend fun login(loginRequest: LoginRequest) = flow {
        emit(safeApiCall { movieService.login(loginRequest) })
    }
}