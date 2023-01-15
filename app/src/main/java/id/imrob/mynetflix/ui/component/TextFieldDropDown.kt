package id.imrob.mynetflix.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import id.imrob.mynetflix.ui.theme.Gray
import id.imrob.mynetflix.ui.theme.Placeholder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDropDown(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    itemDropDown: List<String>,
    onValueChange: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var rowSize by remember { mutableStateOf(Size.Zero) }

    Box(modifier = modifier
        .fillMaxWidth()
        .onGloballyPositioned { layoutCoordinates ->
            rowSize = layoutCoordinates.size.toSize()
        }) {
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onValueChange(it) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Gray,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                textColor = Color.White
            ),
            label = {
                Text(text = label, color = Placeholder)
            },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        tint = Placeholder
                    )
                }
            },
            shape = RoundedCornerShape(16.dp)
        )

        DropdownMenu(
            modifier = Modifier
                .background(Gray)
                .width(with(LocalDensity.current) { rowSize.width.toDp() }),
            expanded = expanded,
            onDismissRequest = { expanded = false })
        {
            itemDropDown.forEach {
                DropdownMenuItem(
                    onClick = {
                        onValueChange(it)
                        expanded = false
                    },
                    text = {
                        Text(text = it, color = Color.White)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDropDown() {
//    TextFieldDropDown(text = "", label = "Gender", onValueChange = {})
}