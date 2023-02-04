package id.imrob.mynetflix.ui.screen.auth.register

import id.imrob.mynetflix.core.data.remote.response.RegisterResponse

sealed class RegisterScreenState {
    class Success(val user: RegisterResponse): RegisterScreenState()
    class Error(val message: String): RegisterScreenState()
    object Empty: RegisterScreenState()
    object Loading: RegisterScreenState()
}
