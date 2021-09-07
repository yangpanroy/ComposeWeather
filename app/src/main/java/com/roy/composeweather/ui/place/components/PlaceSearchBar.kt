package com.roy.composeweather.ui.place.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PlaceSearchBar(keywords: String, setKeywords: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.primary,
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Surface(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(4.dp)
            ) {
                OutlinedTextField(
                    value = keywords,
                    onValueChange = {
                        setKeywords(it)
                    },
                    placeholder = { Text("请输入地址") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.surface,
                        unfocusedBorderColor = MaterialTheme.colors.surface
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}