/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
            delay(1000)
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
