package id.imrob.mynetflix.core.domain.usecase


import id.imrob.mynetflix.core.data.remote.Resource
import id.imrob.mynetflix.core.data.remote.request.LoginRequest
import id.imrob.mynetflix.core.data.remote.request.RegisterRequest
import id.imrob.mynetflix.core.data.remote.response.LoginReponse
import id.imrob.mynetflix.core.data.remote.response.RegisterResponse
import id.imrob.mynetflix.core.data.remote.response.WebResponse
import kotlinx.coroutines.flow.Flow

interface IAuthInteractor {
    suspend fun login(loginRequest: LoginRequest): Flow<Resource<WebResponse<LoginReponse>>>
    suspend fun register(registerRequest: RegisterRequest): Flow<Resource<WebResponse<RegisterResponse>>>
    suspend fun getIsLoggedIn(): Flow<Boolean>
    suspend fun getCurrentUserName(): Flow<String>
    suspend fun storeUsername(email: String)
    suspend fun storeToken(token: String)
}