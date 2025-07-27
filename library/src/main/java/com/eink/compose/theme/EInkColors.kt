package com.eink.compose.theme

import androidx.compose.ui.graphics.Color

/**
 * E-Ink optimized color palette following the 16-level grayscale guideline.
 * Colors are designed for maximum contrast and readability on electrophoretic displays.
 */
object EInkColors {
    
    // Pure black and white for maximum contrast
    val Pure_Black = Color(0xFF000000)
    val Pure_White = Color(0xFFFFFFFF)
    
    // 16-level grayscale palette (excluding pure black and white)
    val Gray_01 = Color(0xFF111111) // Darkest gray
    val Gray_02 = Color(0xFF222222)
    val Gray_03 = Color(0xFF333333)
    val Gray_04 = Color(0xFF444444)
    val Gray_05 = Color(0xFF555555)
    val Gray_06 = Color(0xFF666666)
    val Gray_07 = Color(0xFF777777)
    val Gray_08 = Color(0xFF888888) // Middle gray
    val Gray_09 = Color(0xFF999999)
    val Gray_10 = Color(0xFFAAAAAA)
    val Gray_11 = Color(0xFFBBBBBB)
    val Gray_12 = Color(0xFFCCCCCC)
    val Gray_13 = Color(0xFFDDDDDD)
    val Gray_14 = Color(0xFFEEEEEE) // Lightest gray
    
    /**
     * High contrast color scheme for maximum readability.
     * Uses only pure black and white.
     */
    object HighContrast {
        val primary = Pure_Black
        val onPrimary = Pure_White
        val primaryContainer = Pure_White
        val onPrimaryContainer = Pure_Black
        val secondary = Pure_Black
        val onSecondary = Pure_White
        val secondaryContainer = Pure_White
        val onSecondaryContainer = Pure_Black
        val background = Pure_White
        val onBackground = Pure_Black
        val surface = Pure_White
        val onSurface = Pure_Black
        val surfaceVariant = Pure_White
        val onSurfaceVariant = Pure_Black
        val outline = Pure_Black
        val error = Pure_Black
        val onError = Pure_White
    }
    
    /**
     * Grayscale color scheme with subtle hierarchy.
     * Uses the full 16-level grayscale palette for visual separation.
     */
    object Grayscale {
        val primary = Pure_Black
        val onPrimary = Pure_White
        val primaryContainer = Gray_13  // Light gray for containers
        val onPrimaryContainer = Pure_Black
        val secondary = Gray_05         // Dark gray for secondary elements
        val onSecondary = Pure_White
        val secondaryContainer = Gray_12
        val onSecondaryContainer = Pure_Black
        val background = Pure_White
        val onBackground = Pure_Black
        val surface = Pure_White
        val onSurface = Pure_Black
        val surfaceVariant = Gray_14    // Very light gray for subtle differentiation
        val onSurfaceVariant = Pure_Black
        val outline = Gray_03           // Dark gray for borders
        val error = Pure_Black
        val onError = Pure_White
    }
    
    /**
     * Dark theme variants for both high contrast and grayscale modes.
     */
    object DarkHighContrast {
        val primary = Pure_White
        val onPrimary = Pure_Black
        val primaryContainer = Pure_Black
        val onPrimaryContainer = Pure_White
        val secondary = Pure_White
        val onSecondary = Pure_Black
        val secondaryContainer = Pure_Black
        val onSecondaryContainer = Pure_White
        val background = Pure_Black
        val onBackground = Pure_White
        val surface = Pure_Black
        val onSurface = Pure_White
        val surfaceVariant = Pure_Black
        val onSurfaceVariant = Pure_White
        val outline = Pure_White
        val error = Pure_White
        val onError = Pure_Black
    }
    
    object DarkGrayscale {
        val primary = Pure_White
        val onPrimary = Pure_Black
        val primaryContainer = Gray_03  // Dark gray for containers
        val onPrimaryContainer = Pure_White
        val secondary = Gray_10         // Light gray for secondary elements
        val onSecondary = Pure_Black
        val secondaryContainer = Gray_04
        val onSecondaryContainer = Pure_White
        val background = Pure_Black
        val onBackground = Pure_White
        val surface = Gray_01           // Near black for surfaces
        val onSurface = Pure_White
        val surfaceVariant = Gray_02    // Very dark gray for subtle differentiation
        val onSurfaceVariant = Pure_White
        val outline = Gray_12           // Light gray for borders
        val error = Pure_White
        val onError = Pure_Black
    }
}