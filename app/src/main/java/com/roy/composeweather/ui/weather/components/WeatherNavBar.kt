package com.roy.composeweather.ui.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.roy.composeweather.R
import com.roy.composeweather.ui.theme.TextRevert

@Preview
@Composable
fun WeatherNavBar(
    modifier: Modifier = Modifier,
    city: String = "",
    onClickIcon: () -> Unit = {},
) {
    ConstraintLayout(
        constraintSet = ConstraintSet {
            val searchIcon = createRefFor("searchIcon")
            val cityLabel = createRefFor("cityLabel")

            constrain(searchIcon) {
                start.linkTo(parent.start, 16.dp)
                centerVerticallyTo(parent)
            }

            constrain(cityLabel) {
                centerTo(parent)
            }

        },
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = null,
            modifier = Modifier
                .layoutId("searchIcon")
                .size(30.dp)
                .clickable { onClickIcon() }
        )
        Text(
            text = city,
            color = TextRevert,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .layoutId("cityLabel")
                .widthIn(max = 200.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}