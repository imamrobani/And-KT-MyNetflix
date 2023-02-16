package id.imrob.mynetflix.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import id.imrob.mynetflix.ui.theme.Placeholder


@Composable
fun GhostButton(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = LocalTextStyle.current,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            contentColor = Placeholder,
        ),
        onClick = onClick
    ) {
        Text(text = text, style = style)
    }
}