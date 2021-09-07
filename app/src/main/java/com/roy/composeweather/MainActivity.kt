package com.roy.composeweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.roy.composeweather.logic.model.Place
import com.roy.composeweather.ui.place.Place
import com.roy.composeweather.ui.place.PlaceViewModel
import com.roy.composeweather.ui.theme.ComposeWeatherTheme
import com.roy.composeweather.ui.weather.WeatherActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(PlaceViewModel::class.java)
        if (viewModel.isPlaceSaved()) {
            // 有缓存的地点
            val place = viewModel.getSavedPlace()
            replaceToWeatherPage(place)
            return
        }

        setContent {
            ComposeWeatherTheme {
                Place(
                    viewModel = viewModel,
                    onClickPlace = { replaceToWeatherPage(it) }
                )
            }
        }
    }

    // 跳转至天气页面
    private fun replaceToWeatherPage(place: Place) {
        WeatherActivity.startInstance(this, place)
        finish()
    }
}
