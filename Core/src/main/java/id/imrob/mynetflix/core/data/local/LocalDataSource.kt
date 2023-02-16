package id.imrob.mynetflix.core.data.local

import android.util.Log
import id.imrob.mynetflix.core.data.local.datastore.MovieDataStore
import id.imrob.mynetflix.core.data.local.room.dao.UserDao
import id.imrob.mynetflix.core.data.local.room.entity.toEntity
import id.imrob.mynetflix.core.data.local.room.entity.toMovie
import id.imrob.mynetflix.core.data.local.room.entity.toUser
import id.imrob.mynetflix.core.domain.model.Movie
import id.imrob.mynetflix.core.domain.model.User
import id.imrob.mynetflix.core.domain.model.toUserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class LocalDataSource constructor(
    private val userDao: UserDao,
    private val movieDataStore: MovieDataStore
) {

    suspend fun isLoggedIn() = flow {
        movieDataStore.token.collect {
            emit(it.isNotEmpty())
        }
    }.catch {
        Log.e("LocalDataSource", "isLoggedIn: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun getCurrentUsername() = flow {
        movieDataStore.username.collect{ emit(it)}
    }.catch {
        Log.e("LocalDataSource", "getCurrentUsername: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun storeUsername(username: String) = movieDataStore.storeData(MovieDataStore.USERNAME, username)
    suspend fun storeToken(token: String) = movieDataStore.storeData(MovieDataStore.TOKEN, token)

    suspend fun getAllFavoriteMovie() = flow {
        userDao.getAllFavoriteMovie().collect{ emit(it.map { entity -> entity.toMovie() })}
    }.catch {
        Log.e("LocalDataSource", "getAllFavoriteMovie: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun isMMovieFavorite(id: String) = flow {
        val isExist = userDao.getFavoriteMovieById(id)
        emit(isExist)
    }.catch {
        Log.e("LocalDataSource", "isMMovieFavorite: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun addFavoriteMovie(movie: Movie) = flow {
        userDao.addFavoriteMovie(movie.toEntity())
        emit(true)
    }.catch {
        Log.e("LocalDataSource", "addFavoriteMovie: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun removeMovieFromFavorite(movie: Movie) = flow {
        userDao.removeMovieFromFavorite(movie.toEntity())
        emit(true)
    }.catch {
        Log.e("LocalDataSource", "removeMovieFromFavorite: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

}