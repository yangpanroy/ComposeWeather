package com.roy.composeweather.ui.weather.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roy.composeweather.ui.theme.TextRevert

@Composable
fun MainInfo(
    modifier: Modifier = Modifier,
    temperature: String,
    skycon: String,
    aqi: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        MainTemperature(temperature = temperature)
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            SubInfo(skycon)
            Spacer(modifier = Modifier.width(13.dp))
            SubInfo("|")
            Spacer(modifier = Modifier.width(13.dp))
            SubInfo("空气指数 $aqi")
            Spacer(modifier = Modifier.width(13.dp))
        }
    }
}

@Composable
fun MainTemperature(temperature: String = "") {
    Text(
        text = "$temperature℃",
        color = TextRevert,
        fontSize = 70.sp
    )
}

@Composable
fun SubInfo(info: String = "") {
    Text(
        text = info,
        color = TextRevert,
        fontSize = 18.sp
    )
}