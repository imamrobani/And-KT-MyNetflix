package id.imrob.mynetflix.domain.repository

import id.imrob.mynetflix.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
  suspend fun login(email: String, password: String): Flow<List<User>>
  suspend fun register(user: User): Flow<User>
  suspend fun getIsLoggedIn(): Flow<Boolean>
  suspend fun storeEmail(email: String)
}