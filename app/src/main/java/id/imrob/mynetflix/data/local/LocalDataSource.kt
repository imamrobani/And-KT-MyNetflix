package id.imrob.mynetflix.data.local

import android.util.Log
import id.imrob.mynetflix.data.local.datastore.MovieDataStore
import id.imrob.mynetflix.data.local.room.dao.UserDao
import id.imrob.mynetflix.data.local.room.entity.toUser
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
    Log.d("LocalDataSource", "LoginUser: failed=${it.message}")
  }.flowOn(Dispatchers.IO)

}