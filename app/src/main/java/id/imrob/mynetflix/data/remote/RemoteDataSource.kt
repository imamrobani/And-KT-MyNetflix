package id.imrob.mynetflix.data.remote

import android.util.Log
import id.imrob.mynetflix.data.remote.network.MovieService
import id.imrob.mynetflix.data.remote.response.toListMovie
import id.imrob.mynetflix.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val movieService: MovieService
) {
    suspend fun getNowPlayingMovie() = flow<List<Movie>> {
        movieService.getNowPlaying().toListMovie().let { emit(it) }
    }.catch {
        Log.d("RemoteDataSource", "getNowPlayingMovie: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)
}