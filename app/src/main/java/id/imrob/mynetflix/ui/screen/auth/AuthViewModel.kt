package id.imrob.mynetflix.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.imrob.mynetflix.MovieApplication
import id.imrob.mynetflix.core.data.AuthRepository
import id.imrob.mynetflix.core.data.remote.Resource
import id.imrob.mynetflix.core.data.remote.request.LoginRequest
import id.imrob.mynetflix.core.data.remote.request.RegisterRequest
import id.imrob.mynetflix.ui.screen.auth.login.LoginScreenState
import id.imrob.mynetflix.ui.screen.auth.register.RegisterScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginScreenState = MutableStateFlow<LoginScreenState>(LoginScreenState.Empty)
    val userLogin: StateFlow<LoginScreenState> get() = _loginScreenState

    private val _registerScreenState =
        MutableStateFlow<RegisterScreenState>(RegisterScreenState.Empty)
    val userRegister: StateFlow<RegisterScreenState> get() = _registerScreenState

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication
                AuthViewModel(application.appMovieContainer.authRepository)
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginScreenState.value = LoginScreenState.Loading
            authRepository.login(LoginRequest(password, email)).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _loginScreenState.value = LoginScreenState.Success(result.data.data)
                    }
                    is Resource.Error -> {
                        _loginScreenState.value = LoginScreenState.Error(result.msg)
                    }
                    else -> Unit
                }
            }
        }
    }

    fun register(request: RegisterRequest) {
        viewModelScope.launch {
            _registerScreenState.value = RegisterScreenState.Loading
            authRepository.register(request).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _registerScreenState.value = RegisterScreenState.Success(result.data.data)
                    }
                    is Resource.Error -> {
                        _registerScreenState.value = RegisterScreenState.Error(result.msg)
                    }
                    else -> Unit
                }
            }
        }
    }

    fun storeEmail(email: String) {
        viewModelScope.launch {
            authRepository.storeEmail(email)
        }
    }

    fun storeToken(token: String) {
        viewModelScope.launch {
            authRepository.storeToken(token)
        }
    }

}