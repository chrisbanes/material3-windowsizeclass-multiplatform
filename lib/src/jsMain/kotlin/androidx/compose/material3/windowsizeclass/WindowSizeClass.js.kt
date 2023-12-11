// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.compose.material3.windowsizeclass

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.browser.window
import org.w3c.dom.Window
import org.w3c.dom.events.Event

@ExperimentalMaterial3WindowSizeClassApi
@Composable
actual fun calculateWindowSizeClass(): WindowSizeClass {
    var windowSizeClass by remember {
        mutableStateOf(
            WindowSizeClass.calculateFromSize(window.getDpSize()),
        )
    }

    // Add a listener and listen for resize events
    DisposableEffect(Unit) {
        val callback: (Event) -> Unit = {
            windowSizeClass = WindowSizeClass.calculateFromSize(window.getDpSize())
        }

        window.addEventListener("resize", callback)

        onDispose {
            window.removeEventListener("resize", callback)
        }
    }

    return windowSizeClass
}

private fun Window.getDpSize(): DpSize = DpSize(innerWidth.dp, innerHeight.dp)
