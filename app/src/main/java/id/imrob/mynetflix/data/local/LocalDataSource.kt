package id.imrob.mynetflix.data.local

import android.util.Log
import id.imrob.mynetflix.data.local.datastore.MovieDataStore
import id.imrob.mynetflix.data.local.room.dao.UserDao
import id.imrob.mynetflix.data.local.room.entity.toUser
import id.imrob.mynetflix.domain.model.User
import id.imrob.mynetflix.domain.model.toUserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class LocalDataSource constructor(
    private val userDao: UserDao,
    private val movieDataStore: MovieDataStore
) {

    suspend fun loginUser(email: String, password: String) = flow {
        emitAll(
            userDao.getUserByEmailAndPassword(email, password).map {
                it.map {
                    it.toUser()
                }
            }
        )
    }.catch {
        Log.e("LocalDataSource", "LoginUser: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun registerUser(user: User) = flow {
        userDao.storeUser(user.toUserEntity())
        emit(user)
    }.catch {
        Log.e("LocalDataSource", "registerUser: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun isLoggedIn() = flow {
        movieDataStore.token.collect {
            emit(it.isNotEmpty())
        }
    }.catch {
        Log.e("LocalDataSource", "isLoggedIn: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun storeEmail(email: String) = movieDataStore.storeData(MovieDataStore.EMAIL, email)

    suspend fun storeToken(token: String) = movieDataStore.storeData(MovieDataStore.TOKEN, token)

}