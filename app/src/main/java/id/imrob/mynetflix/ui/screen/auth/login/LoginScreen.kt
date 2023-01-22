package id.imrob.mynetflix.ui.screen.auth.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import id.imrob.mynetflix.R
import id.imrob.mynetflix.ui.Routers
import id.imrob.mynetflix.ui.component.*
import id.imrob.mynetflix.ui.screen.auth.AuthViewModel
import id.imrob.mynetflix.ui.screen.auth.register.RegisterScreenState
import id.imrob.mynetflix.ui.theme.MyNetflixTheme

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AuthViewModel.Factory)
) {

    val context = LocalContext.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val userLoginResponse by viewModel.userLogin.collectAsState()

    LaunchedEffect(userLoginResponse) {
        when (userLoginResponse) {
            is LoginScreenState.Success -> {
                viewModel.storeToken((userLoginResponse as LoginScreenState.Success).user.token)
                navHostController.navigate(Routers.HOME)
            }
            is LoginScreenState.Error -> {
                Toast.makeText(
                    context,
                    (userLoginResponse as LoginScreenState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Unit
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        MovieAppBar()
    }) { contentPadding ->
        Box(
            modifier = Modifier
                .background(Color.Black)
                .padding(contentPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
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

            if (userLoginResponse is LoginScreenState.Loading) {
                CircularProgressIndicator(color = Color.White)
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