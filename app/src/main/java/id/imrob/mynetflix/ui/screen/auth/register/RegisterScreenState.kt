package id.imrob.mynetflix.ui.screen.auth.register

import id.imrob.mynetflix.data.remote.response.RegisterReponse

sealed class RegisterScreenState {
    class Success(val user: RegisterReponse): RegisterScreenState()
    class Error(val message: String): RegisterScreenState()
    object Empty: RegisterScreenState()
    object Loading: RegisterScreenState()
}
