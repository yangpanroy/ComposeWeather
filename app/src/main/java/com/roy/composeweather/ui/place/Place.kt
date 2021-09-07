package com.roy.composeweather.ui.place

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.roy.composeweather.R
import com.roy.composeweather.common.LogUtils
import com.roy.composeweather.logic.model.Place
import com.roy.composeweather.ui.place.components.PlaceItem
import com.roy.composeweather.ui.place.components.PlaceSearchBar

@Composable
fun Place(
    viewModel: PlaceViewModel,
    onClickPlace: (Place) -> Unit = {}
) {
    val (keywords, setKeywords) = remember { mutableStateOf("") }
    val placeList = viewModel.placeList.observeAsState(initial = emptyList())


    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PlaceSearchBar(keywords) {
                setKeywords(it)
                LogUtils.e("输入地址： $it")
                viewModel.searchPlaces(it)
            }

            if (placeList.value.isEmpty() || keywords.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.bg_place),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(placeList.value.size) {
                        val place = placeList.value[it]
                        PlaceItem(
                            name = place.name,
                            desc = place.address,
                            onClick = {
                                onClickPlace(place)
                                viewModel.savePlace(place)
                            }
                        )
                    }
                }
            }
        }
    }
}