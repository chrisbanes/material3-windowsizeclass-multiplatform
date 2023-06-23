package com.example.sampleshared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Sample() {
    Surface(Modifier.fillMaxSize()) {
        val windowSizeClass = calculateWindowSizeClass()
        Column {
            Text(text = "width class")
            Text(text = windowSizeClass.widthSizeClass.toString())
            Text(text = "height class")
            Text(text = windowSizeClass.heightSizeClass.toString())
        }
    }
}
