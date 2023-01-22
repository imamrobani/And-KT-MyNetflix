package id.imrob.mynetflix.data

import id.imrob.mynetflix.data.local.LocalDataSource
import id.imrob.mynetflix.data.remote.RemoteDataSource
import id.imrob.mynetflix.data.remote.Resource
import id.imrob.mynetflix.data.remote.request.LoginRequest
import id.imrob.mynetflix.data.remote.request.RegisterRequest
import id.imrob.mynetflix.data.remote.response.LoginReponse
import id.imrob.mynetflix.data.remote.response.RegisterReponse
import id.imrob.mynetflix.data.remote.response.WebResponse
import id.imrob.mynetflix.domain.model.User
import id.imrob.mynetflix.domain.repository.IAuthRepository
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

    override suspend fun register(registerRequest: RegisterRequest): Flow<Resource<WebResponse<RegisterReponse>>> {
        return remoteDataSource.register(registerRequest)
    }

    override suspend fun getIsLoggedIn(): Flow<Boolean> {
        return localDataSource.isLoggedIn()
    }

    override suspend fun storeEmail(email: String) {
        return localDataSource.storeEmail(email)
    }

    override suspend fun storeToken(token: String) {
        return localDataSource.storeToken(token)
    }
}