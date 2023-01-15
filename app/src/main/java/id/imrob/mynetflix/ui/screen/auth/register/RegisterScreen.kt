package id.imrob.mynetflix.ui.screen.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import id.imrob.mynetflix.R
import id.imrob.mynetflix.ui.component.*
import id.imrob.mynetflix.ui.screen.auth.AuthViewModel
import id.imrob.mynetflix.ui.theme.Gray
import id.imrob.mynetflix.ui.theme.MyNetflixTheme
import id.imrob.mynetflix.ui.theme.Placeholder
import id.imrob.mynetflix.ui.theme.RedNetflix

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AuthViewModel.Factory)
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }
    val listOfGender = listOf("Male", "Female")

    val userRegisterResponse by viewModel.userRegister.collectAsState()

    LaunchedEffect(userRegisterResponse) {
        userRegisterResponse?.let { user ->
            navHostController.popBackStack()
        }
    }

    Scaffold(
        modifier = Modifier
            .background(Color.Black)
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        topBar = { MovieAppBar() }) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(contentPadding),
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
                itemDropDown = listOfGender ,
                onValueChange ={ gender = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlineButton(text = stringResource(R.string.register).uppercase()) {
                viewModel.register(email, password)
            }

            GhostButton(text = stringResource(R.string.have_account).uppercase()) {
                navHostController.popBackStack()
            }

            Spacer(modifier = Modifier.weight(1f))
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