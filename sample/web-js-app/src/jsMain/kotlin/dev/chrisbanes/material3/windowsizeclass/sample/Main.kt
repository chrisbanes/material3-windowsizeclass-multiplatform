package dev.chrisbanes.material3.windowsizeclass.sample

import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("Sample") {
            Sample()
        }
    }
}
