package id.imrob.mynetflix.domain.repository

import id.imrob.mynetflix.data.remote.Resource
import id.imrob.mynetflix.data.remote.request.LoginRequest
import id.imrob.mynetflix.data.remote.request.RegisterRequest
import id.imrob.mynetflix.data.remote.response.LoginReponse
import id.imrob.mynetflix.data.remote.response.RegisterReponse
import id.imrob.mynetflix.data.remote.response.WebResponse
import id.imrob.mynetflix.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
  suspend fun login(loginRequest: LoginRequest): Flow<Resource<WebResponse<LoginReponse>>>
  suspend fun register(registerRequest: RegisterRequest): Flow<Resource<WebResponse<RegisterReponse>>>
  suspend fun getIsLoggedIn(): Flow<Boolean>
  suspend fun storeEmail(email: String)
  suspend fun storeToken(token: String)
}