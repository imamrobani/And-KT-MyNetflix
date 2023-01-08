package id.imrob.mynetflix.data

import id.imrob.mynetflix.data.local.LocalDataSource
import id.imrob.mynetflix.domain.model.User
import id.imrob.mynetflix.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepository(
  private val localDataSource: LocalDataSource
) : IAuthRepository {
  override suspend fun login(email: String, password: String): Flow<List<User>> {
    return localDataSource.loginUser(email, password)
  }

  override suspend fun register(user: User): Flow<User> {
   return localDataSource.registerUser(user)
  }

  override suspend fun getIsLoggedIn(): Flow<Boolean> {
   return localDataSource.isLoggedIn()
  }

  override suspend fun storeEmail(email: String) {
    return localDataSource.storeEmail(email)
  }
}