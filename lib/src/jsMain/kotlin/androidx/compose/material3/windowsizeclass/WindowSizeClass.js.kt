// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress(
    "INVISIBLE_MEMBER",
    "INVISIBLE_REFERENCE",
    "EXPOSED_PARAMETER_TYPE",
) // WORKAROUND: ComposeWindow and ComposeLayer are internal

package androidx.compose.material3.windowsizeclass

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import kotlinx.browser.window
import org.w3c.dom.Window
import org.w3c.dom.events.Event

@ExperimentalMaterial3WindowSizeClassApi
@Composable
actual fun calculateWindowSizeClass(): WindowSizeClass {
    val density = LocalDensity.current

    var windowSizeClass by remember {
        mutableStateOf(WindowSizeClass.calculateFromSize(window.getDpSize(density)))
    }

    // Add a listener and listen for resize events
    DisposableEffect(density) {
        val callback: (Event) -> Unit = {
            windowSizeClass = WindowSizeClass.calculateFromSize(window.getDpSize(density))
        }

        window.addEventListener("resize", callback)

        onDispose {
            window.removeEventListener("resize", callback)
        }
    }

    return windowSizeClass
}

private fun Window.getDpSize(density: Density): DpSize = with(density) {
    DpSize(innerWidth.toDp(), innerHeight.toDp())
}
