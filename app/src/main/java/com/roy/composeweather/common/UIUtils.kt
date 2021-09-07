package com.roy.composeweather.common

import android.widget.Toast
import com.roy.composeweather.MainApp

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MainApp.context, this, duration).show()
}

fun String.showLongToast() {
    this.showToast(Toast.LENGTH_LONG)
}


