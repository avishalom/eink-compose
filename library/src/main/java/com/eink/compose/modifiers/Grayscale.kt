package com.eink.compose.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.geometry.Rect

/**
 * Applies a grayscale filter to remove all color saturation.
 * This modifier ensures absolute compliance with E-Ink guideline #1 by converting
 * any colored content to grayscale, including dynamically loaded images.
 * 
 * This should be applied at the root of the UI to guarantee that all rendered
 * content is converted to grayscale, serving as a safety net for any content
 * that might bypass the theme system.
 */
fun Modifier.grayscale(): Modifier = this.then(GrayscaleModifier)

private object GrayscaleModifier : DrawModifier {
    // Cached objects to avoid allocations on every draw
    private val saturationMatrix = ColorMatrix().apply { 
        setToSaturation(0f) // 0f = completely desaturated (grayscale)
    }
    private val saturationFilter = ColorFilter.colorMatrix(saturationMatrix)
    private val paint = Paint().apply {
        colorFilter = saturationFilter
    }
    
    override fun ContentDrawScope.draw() {
        // Draw content into a layer with the grayscale filter applied
        drawIntoCanvas { canvas ->
            canvas.saveLayer(
                bounds = Rect(0f, 0f, size.width, size.height),
                paint = paint
            )
            drawContent()
            canvas.restore()
        }
    }
}

/**
 * Alternative grayscale implementation that converts colors to their luminance equivalent.
 * This provides more control over the grayscale conversion process and may produce
 * better results for certain types of content.
 */
fun Modifier.luminanceGrayscale(): Modifier = this.then(LuminanceGrayscaleModifier)

private object LuminanceGrayscaleModifier : DrawModifier {
    // Cached objects to avoid allocations on every draw
    // Standard luminance weights (ITU-R BT.709)
    private val luminanceMatrix = ColorMatrix(
        floatArrayOf(
            0.2126f, 0.7152f, 0.0722f, 0f, 0f,  // Red channel
            0.2126f, 0.7152f, 0.0722f, 0f, 0f,  // Green channel
            0.2126f, 0.7152f, 0.0722f, 0f, 0f,  // Blue channel
            0f, 0f, 0f, 1f, 0f                   // Alpha channel (unchanged)
        )
    )
    private val luminanceFilter = ColorFilter.colorMatrix(luminanceMatrix)
    private val paint = Paint().apply {
        colorFilter = luminanceFilter
    }
    
    override fun ContentDrawScope.draw() {
        drawIntoCanvas { canvas ->
            canvas.saveLayer(
                bounds = Rect(0f, 0f, size.width, size.height),
                paint = paint
            )
            drawContent()
            canvas.restore()
        }
    }
}