package id.imrob.mynetflix.ui.screen.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import id.imrob.mynetflix.R
import id.imrob.mynetflix.ui.Routers
import id.imrob.mynetflix.ui.component.MovieAppBar
import id.imrob.mynetflix.ui.screen.auth.AuthViewModel
import id.imrob.mynetflix.ui.theme.Gray
import id.imrob.mynetflix.ui.theme.MyNetflixTheme
import id.imrob.mynetflix.ui.theme.Placeholder

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
  navHostController: NavHostController,
  viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AuthViewModel.Factory)
) {

  var email by rememberSaveable { mutableStateOf("") }
  var password by rememberSaveable { mutableStateOf("") }
  var passwordVisible by rememberSaveable { mutableStateOf(false) }

  val userLoginResponse by viewModel.userLogin.collectAsState()

  LaunchedEffect(userLoginResponse) {
    userLoginResponse?.let { user ->
      viewModel.storeEmail(user.email)
      navHostController.navigate(Routers.HOME)
    }
  }

  Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
    MovieAppBar()
  }) { contentPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(contentPadding)
        .background(Color.Black),
      verticalArrangement = Arrangement.Center
    ) {
      TextField(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        value = email,
        onValueChange = { email = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
          containerColor = Gray,
          unfocusedBorderColor = Color.Transparent,
          focusedBorderColor = Color.Transparent,
          textColor = Color.White
        ),
        label = {
          Text(text = stringResource(R.string.email), color = Placeholder)
        },
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Email
        ),
        shape = RoundedCornerShape(16.dp)
      )

      TextField(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .padding(top = 16.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        value = password,
        onValueChange = { password = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
          containerColor = Gray,
          unfocusedBorderColor = Color.Transparent,
          focusedBorderColor = Color.Transparent,
          textColor = Color.White
        ),
        label = {
          Text(text = stringResource(R.string.password), color = Placeholder)
        },
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Password
        ),
        trailingIcon = {
          val image = if (passwordVisible) Icons.Filled.Visibility
          else Icons.Filled.VisibilityOff

          IconButton(onClick = {
            passwordVisible = !passwordVisible
          }) {
            Icon(imageVector = image, contentDescription = null, tint = Placeholder)
          }
        },
        shape = RoundedCornerShape(16.dp)
      )

      Button(
        onClick = { viewModel.login(email, password) },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .padding(top = 32.dp),
      ) {
        Text(text = stringResource(R.string.login))
      }

      TextButton(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .padding(top = 64.dp),
        colors = ButtonDefaults.textButtonColors(
          contentColor = Placeholder,
        ),
        onClick = {
          navHostController.navigate(Routers.REGISTER)
        }
      ) {
        Text(text = stringResource(id = R.string.register_new_account).uppercase(), color = Placeholder)
      }

    }
  }

}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
  MyNetflixTheme {
    LoginScreen(navHostController = rememberNavController())
  }
}