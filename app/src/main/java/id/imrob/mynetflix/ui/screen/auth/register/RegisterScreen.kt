package id.imrob.mynetflix.ui.screen.auth.register

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
import id.imrob.mynetflix.data.remote.request.RegisterRequest
import id.imrob.mynetflix.ui.component.*
import id.imrob.mynetflix.ui.screen.auth.AuthViewModel
import id.imrob.mynetflix.ui.theme.MyNetflixTheme

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AuthViewModel.Factory)
) {

    val context = LocalContext.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }
    val listOfGender = listOf("Male", "Female")

    val userRegisterResponse by viewModel.userRegister.collectAsState()

    val registerRequest = RegisterRequest(
        password, date, gender.toIntOrNull() ?: 1, email, username
    )

    LaunchedEffect(userRegisterResponse) {
        when (userRegisterResponse) {
            is RegisterScreenState.Success -> {
                navHostController.popBackStack()
            }
            is RegisterScreenState.Error -> {
                Toast.makeText(
                    context,
                    (userRegisterResponse as RegisterScreenState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Unit
        }
    }

    Scaffold(
        modifier = Modifier
            .background(Color.Black)
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        topBar = { MovieAppBar() }) { contentPadding ->
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                TextFieldEmail(email = email, onValueChange = { email = it })
                TextFieldPassword(password = password, onValueChange = { password = it })
                TextFieldUserName(username = username, onValueChange = { username = it })
                TextFieldBirthDate(date = date, onValueChange = { date = it })
                TextFieldDropDown(
                    text = gender,
                    label = "Gender",
                    itemDropDown = listOfGender,
                    onValueChange = { gender = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlineButton(text = stringResource(R.string.register).uppercase()) {
                    viewModel.register(registerRequest)
                }

                GhostButton(text = stringResource(R.string.have_account).uppercase()) {
                    navHostController.popBackStack()
                }

                Spacer(modifier = Modifier.weight(1f))
            }

            if (userRegisterResponse is RegisterScreenState.Loading) {
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
        RegisterScreen(navHostController = rememberNavController())
    }
}