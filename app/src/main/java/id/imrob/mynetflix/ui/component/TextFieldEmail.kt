package id.imrob.mynetflix.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import id.imrob.mynetflix.R
import id.imrob.mynetflix.ui.theme.Gray
import id.imrob.mynetflix.ui.theme.Placeholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldEmail(
    modifier: Modifier = Modifier,
    email: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = email,
        onValueChange = { onValueChange(it) },
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
}