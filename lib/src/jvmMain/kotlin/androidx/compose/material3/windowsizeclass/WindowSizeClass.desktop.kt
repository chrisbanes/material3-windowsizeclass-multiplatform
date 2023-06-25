// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.compose.material3.windowsizeclass

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.awt.Component
import java.awt.Window
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

@ExperimentalMaterial3WindowSizeClassApi
@Composable
actual fun calculateWindowSizeClass(): WindowSizeClass {
    var window: Window? by remember { mutableStateOf(null) }
    var size by remember { mutableStateOf(DpSize.Zero) }

    LaunchedEffect(Unit) {
        // Yes, this is gross. The LocalWindow composition local is internal, so we need a
        // 'static' way to retrieve the current window. The only way I could think of was
        // to poll Window.getWindows(). This might not work in all situations.
        while (isActive) {
            window = Window.getWindows().firstOrNull { it.isActive }
            // Delay for the next poll. If we haven't got a window yet, poll quicker.
            delay(if (window == null) 50 else 1000)
        }
    }

    // Add a listener and listen for componentResized events
    DisposableEffect(window) {
        window?.let { size = it.getDpSize() }

        val listener = object : ComponentAdapter() {
            override fun componentResized(event: ComponentEvent) {
                size = window!!.getDpSize()
            }
        }

        window?.addComponentListener(listener)

        onDispose {
            window?.removeComponentListener(listener)
        }
    }

    return WindowSizeClass.calculateFromSize(size)
}

private fun Component.getDpSize(): DpSize = DpSize(width.dp, height.dp)
