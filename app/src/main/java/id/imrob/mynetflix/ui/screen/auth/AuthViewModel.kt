package id.imrob.mynetflix.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.imrob.mynetflix.MovieApplication
import id.imrob.mynetflix.data.AuthRepository
import id.imrob.mynetflix.domain.model.User
import id.imrob.mynetflix.ui.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
  private val authRepository: AuthRepository
): ViewModel() {

  private val _user = MutableStateFlow<User?>(null)
  val userLogin: StateFlow<User?> get() = _user

  private val _userRegister = MutableStateFlow<User?>(null)
  val userRegister: StateFlow<User?> get() = _userRegister

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication
        AuthViewModel(application.appMovieContainer.authRepository)
      }
    }
  }

  fun login(email: String, password: String){
    viewModelScope.launch {
      authRepository.login(email, password).collect { users ->
        if(users.isNotEmpty()){
          _user.value = users[0]
        }
      }
    }
  }

  fun register(email: String, password: String){
    viewModelScope.launch {
      authRepository.register(User( name = "", email = email, password = password)).collect{ user ->
        _userRegister.value = user
      }
    }
  }

  fun storeEmail(email: String){
    viewModelScope.launch {
      authRepository.storeEmail(email)
    }
  }

}