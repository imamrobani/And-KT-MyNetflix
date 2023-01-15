package id.imrob.mynetflix.ui.component

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.imrob.mynetflix.R
import id.imrob.mynetflix.ui.theme.Gray
import id.imrob.mynetflix.ui.theme.Placeholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldBirthDate(
    modifier: Modifier = Modifier,
    date: String,
    onValueChange: (String) -> Unit
) {

    val year by remember { mutableStateOf(1999) }
    val month by remember { mutableStateOf(0) }
    val day by remember { mutableStateOf(1) }

    val context = LocalContext.current

    val datePicker = DatePickerDialog(
        context,
        { _, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onValueChange("$mDayOfMonth-${mMonth + 1}-$mYear")
        }, year, month, day
    )

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = date,
        onValueChange = { onValueChange(it) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Gray,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            textColor = Color.White
        ),
        label = {
            Text(text = stringResource(R.string.birthdate), color = Placeholder)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        trailingIcon = {
            IconButton(onClick = { datePicker.show() }) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = null,
                    tint = Placeholder
                )
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}

@Preview
@Composable
private fun PreviewBirthDate() {

}