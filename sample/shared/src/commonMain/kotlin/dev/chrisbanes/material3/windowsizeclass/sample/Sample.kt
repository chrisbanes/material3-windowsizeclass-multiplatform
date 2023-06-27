// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0

package dev.chrisbanes.material3.windowsizeclass.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Sample() {
    Surface(Modifier.fillMaxSize()) {
        val windowSizeClass = calculateWindowSizeClass()

        var recompositionCount by remember { mutableStateOf(0) }
        SideEffect {
            recompositionCount++
            println("Recomposition count: $recompositionCount")
        }

        Column {
            Text(text = "width class")
            Text(text = windowSizeClass.widthSizeClass.toString())
            Text(text = "height class")
            Text(text = windowSizeClass.heightSizeClass.toString())
        }
    }
}
