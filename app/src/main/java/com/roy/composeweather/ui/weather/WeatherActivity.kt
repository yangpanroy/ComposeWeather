package com.roy.composeweather.ui.weather

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.roy.composeweather.logic.model.Place
import com.roy.composeweather.ui.place.Place
import com.roy.composeweather.ui.place.PlaceViewModel
import com.roy.composeweather.ui.theme.ComposeWeatherTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch

class WeatherActivity : ComponentActivity() {

    companion object {
        private const val KEY_LNG = "lng"
        private const val KEY_LAT = "lat"
        private const val KEY_PLACE = "place"

        fun startInstance(context: Context, place: Place) {
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra(KEY_PLACE, Gson().toJson(place))
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val decorView = window.decorView
//        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        window.statusBarColor = Color.TRANSPARENT

        val viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        val placeViewModel = ViewModelProvider(this).get(PlaceViewModel::class.java)
        val placeJson = intent.getStringExtra(KEY_PLACE)
        val placeInfo = Gson().fromJson(placeJson, Place::class.java)
        viewModel.setPlace(placeInfo)

        setContent {
            val scaffoldState = rememberScaffoldState()

            ComposeWeatherTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    drawerContent = {
                        Place(viewModel = placeViewModel) {
                            viewModel.setPlace(it)
                            lifecycleScope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    },
                ) {
                    Weather(viewModel) {
                        lifecycleScope.launch { scaffoldState.drawerState.open() }
                    }
                }
            }
        }

    }
}
