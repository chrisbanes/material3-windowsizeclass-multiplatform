package com.example.androidsample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.sampleshared.MainView

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainView()
    }
}
