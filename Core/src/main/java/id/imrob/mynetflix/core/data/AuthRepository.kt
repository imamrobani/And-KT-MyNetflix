package id.imrob.mynetflix.core.data

import id.imrob.mynetflix.core.data.local.LocalDataSource
import id.imrob.mynetflix.core.data.remote.RemoteDataSource
import id.imrob.mynetflix.core.data.remote.Resource
import id.imrob.mynetflix.core.data.remote.request.LoginRequest
import id.imrob.mynetflix.core.data.remote.request.RegisterRequest
import id.imrob.mynetflix.core.data.remote.response.LoginReponse
import id.imrob.mynetflix.core.data.remote.response.RegisterResponse
import id.imrob.mynetflix.core.data.remote.response.WebResponse
import id.imrob.mynetflix.core.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IAuthRepository {
    override suspend fun login(
        loginRequest: LoginRequest
    ): Flow<Resource<WebResponse<LoginReponse>>> {
        return remoteDataSource.login(loginRequest)
    }

    override suspend fun register(registerRequest: RegisterRequest): Flow<Resource<WebResponse<RegisterResponse>>> {
        return remoteDataSource.register(registerRequest)
    }

    override suspend fun getIsLoggedIn(): Flow<Boolean> {
        return localDataSource.isLoggedIn()
    }

    override suspend fun getCurrentUsername(): Flow<String> {
        return localDataSource.getCurrentUsername()
    }

    override suspend fun storeUsername(email: String) {
        return localDataSource.storeUsername(email)
    }


    override suspend fun storeToken(token: String) {
        return localDataSource.storeToken(token)
    }
}