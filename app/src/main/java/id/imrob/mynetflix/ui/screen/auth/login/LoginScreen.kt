package id.imrob.mynetflix.ui.screen.auth.login

import androidx.compose.foundation.BorderStroke
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
import id.imrob.mynetflix.ui.component.*
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

            TextFieldEmail(
                modifier = Modifier.padding(horizontal = 16.dp),
                email = email,
                onValueChange = { email = it }
            )

            TextFieldPassword(
                modifier = Modifier
                  .padding(horizontal = 16.dp)
                  .padding(top = 16.dp),
                password = password,
                onValueChange = { password = it }
            )

            OutlineButton(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 16.dp)
                  .padding(top = 32.dp),

                text = stringResource(R.string.login).uppercase()
            ) {
                viewModel.login(email, password)
            }

            GhostButton(
                modifier = Modifier
                  .padding(horizontal = 16.dp)
                  .padding(top = 64.dp),
                text = stringResource(id = R.string.register_new_account).uppercase()
            ) {
                navHostController.navigate(Routers.REGISTER)
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