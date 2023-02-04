package id.imrob.mynetflix.ui.screen.auth.login

import id.imrob.mynetflix.core.data.remote.response.LoginReponse

sealed class LoginScreenState {
    class Success(val user: LoginReponse): LoginScreenState()
    class Error(val message: String): LoginScreenState()
    object Empty: LoginScreenState()
    object Loading: LoginScreenState()
}
