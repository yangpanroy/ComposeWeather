package com.roy.composeweather.ui.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.roy.composeweather.R
import com.roy.composeweather.ui.theme.TextPrimary
import com.roy.composeweather.ui.theme.TextSecond
import com.roy.composeweather.ui.weather.model.RealtimeLifeIndex

@Composable
fun LifeComfortPanel(
    modifier: Modifier = Modifier,
    realtimeLifeIndex: RealtimeLifeIndex
) {
    val (coldRisk, carWashing, ultraviolet, dressing) = realtimeLifeIndex

    Card(
        modifier = modifier,
        elevation = 2.dp,
    ) {
        Surface {
            Column {
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "生活指数",
                    color = TextPrimary,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.size(20.dp))
                Row {
                    LifeGridItem(
                        modifier = Modifier.weight(1f),
                        icon = R.drawable.ic_coldrisk,
                        name = "感冒风险",
                        desc = coldRisk
                    )
                    LifeGridItem(
                        modifier = Modifier.weight(1f),
                        icon = R.drawable.ic_dressing,
                        name = "穿衣建议",
                        desc = dressing
                    )
                }
                Row {
                    LifeGridItem(
                        modifier = Modifier.weight(1f),
                        icon = R.drawable.ic_ultraviolet,
                        name = "紫外线强度",
                        desc = ultraviolet
                    )
                    LifeGridItem(
                        modifier = Modifier.weight(1f),
                        icon = R.drawable.ic_carwashing,
                        name = "洗车指数",
                        desc = carWashing
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun LifeGridItem(
    modifier: Modifier = Modifier,
    icon: Int,
    name: String,
    desc: String
) {
    Row(
        modifier = modifier.height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(20.dp))
        Column {
            Text(
                text = name,
                color = TextSecond,
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = desc,
                color = TextPrimary,
                style = MaterialTheme.typography.body1
            )
        }
    }
}