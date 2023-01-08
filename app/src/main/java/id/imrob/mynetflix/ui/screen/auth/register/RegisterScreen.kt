package id.imrob.mynetflix.ui.screen.auth.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import id.imrob.mynetflix.ui.component.MovieAppBar
import id.imrob.mynetflix.ui.screen.auth.AuthViewModel
import id.imrob.mynetflix.ui.theme.MyNetflixTheme
import id.imrob.mynetflix.ui.theme.RedNetflix

@ExperimentalMaterial3Api @Composable fun RegisterScreen(
  navHostController: NavHostController,
  viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AuthViewModel.Factory)
) {

  var email by rememberSaveable { mutableStateOf("") }
  var password by rememberSaveable { mutableStateOf("") }
  var passwordVisible by rememberSaveable { mutableStateOf(false) }

  val userRegisterResponse by viewModel.userRegister.collectAsState()

  LaunchedEffect(userRegisterResponse){
    userRegisterResponse?.let { user ->
      navHostController.popBackStack()
    }
  }

  Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
    MovieAppBar()
  }) { contentPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(contentPadding),
    ) {
      Spacer(modifier = Modifier.weight(1f))
      OutlinedTextField(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        value = email,
        onValueChange = { email = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
          containerColor = MaterialTheme.colorScheme.surface,
          unfocusedBorderColor = Color.LightGray,
          focusedBorderColor = MaterialTheme.colorScheme.onSurface
        ),
        label = {
          Text(text = stringResource(R.string.email))
        },
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Email
        ),
        shape = RoundedCornerShape(16.dp)
      )

      OutlinedTextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        value = password,
        onValueChange = { password = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
          containerColor = MaterialTheme.colorScheme.surface,
          unfocusedBorderColor = Color.LightGray,
          focusedBorderColor = MaterialTheme.colorScheme.onSurface
        ),
        label = {
          Text(text = stringResource(R.string.password))
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
            Icon(imageVector = image, contentDescription = null)
          }
        },
        shape = RoundedCornerShape(16.dp)
      )

      Button(
        onClick = { viewModel.register(email, password) },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .padding(top = 32.dp)
      ) {
        Text(text = stringResource(R.string.register))
      }

      Spacer(modifier = Modifier.weight(1f))

      ClickableText(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .padding(bottom = 32.dp),
        text = buildAnnotatedString {
          withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
            append(stringResource(R.string.have_account))
          }
          withStyle(style = SpanStyle(color = RedNetflix, fontWeight = FontWeight.SemiBold)) {
            append(stringResource(R.string.login))
          }
        },
        style = TextStyle.Default.copy(
          textAlign = TextAlign.Center
        ),
        onClick = {
          navHostController.popBackStack()
        }
      )

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