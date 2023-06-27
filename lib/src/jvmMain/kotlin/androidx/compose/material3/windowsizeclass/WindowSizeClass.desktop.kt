// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package androidx.compose.material3.windowsizeclass

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.LocalWindow
import java.awt.Component
import java.awt.Window
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

@ExperimentalMaterial3WindowSizeClassApi
@Composable
actual fun calculateWindowSizeClass(): WindowSizeClass {
    val window: Window? = LocalWindow.current

    var windowSizeClass by remember(window) {
        mutableStateOf(WindowSizeClass.calculateFromSize(window?.getDpSize() ?: DpSize.Zero))
    }

    // Add a listener and listen for componentResized events
    DisposableEffect(window) {
        val listener = object : ComponentAdapter() {
            override fun componentResized(event: ComponentEvent) {
                windowSizeClass = WindowSizeClass.calculateFromSize(window!!.getDpSize())
            }
        }

        window?.addComponentListener(listener)

        onDispose {
            window?.removeComponentListener(listener)
        }
    }

    return windowSizeClass
}

private fun Component.getDpSize(): DpSize = DpSize(width.dp, height.dp)
