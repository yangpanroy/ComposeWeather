package com.roy.composeweather.ui.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun WeatherHeaderPanel(
    place: String,
    temperature: String,
    skycon: String,
    skyconBackground: Int,
    aqi: String,
    onClickIcon: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(530.dp)
            .fillMaxWidth()
    ) {
        if (skyconBackground == -1) {
            Surface(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxSize()
            ) {}
        } else {
            Image(
                painter = painterResource(id = skyconBackground),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }


        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val navBar = createRef()
            val mainInfo = createRef()

            WeatherNavBar(
                city = place,
                onClickIcon = { onClickIcon() },
                modifier = Modifier
                    .constrainAs(navBar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            MainInfo(
                modifier = Modifier.constrainAs(mainInfo) {
                    centerTo(parent)
                },
                temperature = temperature,
                skycon = skycon,
                aqi = aqi
            )
        }

    }
}