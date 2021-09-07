package com.roy.composeweather.ui.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.roy.composeweather.ui.theme.TextPrimary
import com.roy.composeweather.ui.theme.TextSecond
import com.roy.composeweather.ui.weather.model.BriefForecastItem

@Composable
fun ForecastPanel(
    modifier: Modifier = Modifier,
    briefForecast: List<BriefForecastItem>
) {
    Card(
        modifier = modifier,
        elevation = 2.dp
    ) {
        Surface(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(
                    text = "预报",
                    color = TextPrimary,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.size(20.dp))
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    briefForecast.forEach { briefForecastItem ->
                        ForecastItem(briefForecastItem)
                    }
                }
            }
        }
    }
}

@Composable
fun ForecastItem(
    briefForecastItem: BriefForecastItem
) {
    val (dateStr, skycon, skyconIcon, temperatureStr) = briefForecastItem

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = dateStr,
            color = TextSecond,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.weight(4f)
        )
        Row(
            modifier = Modifier.weight(3f)
        ) {
            Image(
                painter = painterResource(id = skyconIcon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = skycon,
                color = TextSecond,
                style = MaterialTheme.typography.body2,
            )
        }
        Text(
            text = temperatureStr,
            color = TextSecond,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(3f)
        )
    }
}