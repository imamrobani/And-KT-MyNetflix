package id.imrob.mynetflix.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.imrob.mynetflix.MovieApplication
import id.imrob.mynetflix.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel constructor(
  private val authRepository: AuthRepository
): ViewModel() {

  private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
  val isLoggedIn: StateFlow<Boolean?> get() = _isLoggedIn


  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication
        val respository = application.appMovieContainer.authRepository
        MainViewModel(respository)
      }
    }
  }

  fun getIsLoggedInUser() {
    viewModelScope.launch {
      authRepository.getIsLoggedIn().collect{
        _isLoggedIn.value = it
      }
    }
  }

}