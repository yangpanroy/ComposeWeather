package com.roy.composeweather.ui.place.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.roy.composeweather.ui.theme.TextPrimary
import com.roy.composeweather.ui.theme.TextSecond

@Composable
fun PlaceItem(name: String, desc: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(
                    text = name,
                    color = TextPrimary,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = desc,
                    color = TextSecond,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}