@file:Suppress(
    "INVISIBLE_MEMBER",
    "INVISIBLE_REFERENCE",
    "EXPOSED_PARAMETER_TYPE",
) // WORKAROUND: ComposeWindow and ComposeLayer are internal

package dev.chrisbanes.material3.windowsizeclass.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeWindow
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.HTMLTitleElement

private const val CANVAS_ELEMENT_ID = "ComposeTarget" // Hardwired into ComposeWindow

/**
 * A Skiko/Canvas-based top-level window using the browser's entire viewport. Supports resizing.
 */
@Suppress("FunctionName")
fun BrowserViewportWindow(
    title: String = "Untitled",
    content: @Composable ComposeWindow.() -> Unit,
) {
    val htmlHeadElement = document.head!!
    htmlHeadElement.appendChild(
        (document.createElement("style") as HTMLStyleElement).apply {
            type = "text/css"
            appendChild(
                document.createTextNode(
                    """
                    html, body {
                        overflow: hidden;
                        margin: 0 !important;
                        padding: 0 !important;
                    }

                    #$CANVAS_ELEMENT_ID {
                        outline: none;
                    }
                    """.trimIndent(),
                ),
            )
        },
    )

    fun HTMLCanvasElement.fillViewportSize() {
        setAttribute("width", "${window.innerWidth}")
        setAttribute("height", "${window.innerHeight}")
    }

    var canvas = (document.getElementById(CANVAS_ELEMENT_ID) as HTMLCanvasElement).apply {
        fillViewportSize()
    }

    ComposeWindow().apply {
        window.addEventListener("resize", {
            val newCanvas = canvas.cloneNode(false) as HTMLCanvasElement
            canvas.replaceWith(newCanvas)
            canvas = newCanvas

            val scale = layer.layer.contentScale
            newCanvas.fillViewportSize()
            layer.layer.attachTo(newCanvas)
            layer.layer.needRedraw()
            layer.setSize((newCanvas.width / scale).toInt(), (newCanvas.height / scale).toInt())
        })

        // WORKAROUND: ComposeWindow does not implement `setTitle(title)`
        val htmlTitleElement = (
            htmlHeadElement.getElementsByTagName("title").item(0)
                ?: document.createElement("title").also { htmlHeadElement.appendChild(it) }
            ) as HTMLTitleElement
        htmlTitleElement.textContent = title

        setContent {
            content(this)
        }
    }
}
