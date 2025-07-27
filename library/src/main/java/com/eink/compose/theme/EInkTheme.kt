package com.eink.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * E-Ink color scheme variants
 */
enum class EInkColorVariant {
    HighContrast,
    Grayscale
}

/**
 * E-Ink color scheme data class that holds theme colors
 */
@Stable
data class EInkColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val error: Color,
    val onError: Color
)

/**
 * E-Ink typography system
 */
@Stable
data class EInkTypographySystem(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle
)

/**
 * No-ripple theme to disable all ripple effects globally
 */
private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}

/**
 * Create color scheme based on variant and theme
 */
private fun createColorScheme(
    variant: EInkColorVariant,
    darkTheme: Boolean
): EInkColorScheme {
    return when (variant) {
        EInkColorVariant.HighContrast -> {
            if (darkTheme) {
                EInkColorScheme(
                    primary = EInkColors.DarkHighContrast.primary,
                    onPrimary = EInkColors.DarkHighContrast.onPrimary,
                    primaryContainer = EInkColors.DarkHighContrast.primaryContainer,
                    onPrimaryContainer = EInkColors.DarkHighContrast.onPrimaryContainer,
                    secondary = EInkColors.DarkHighContrast.secondary,
                    onSecondary = EInkColors.DarkHighContrast.onSecondary,
                    secondaryContainer = EInkColors.DarkHighContrast.secondaryContainer,
                    onSecondaryContainer = EInkColors.DarkHighContrast.onSecondaryContainer,
                    background = EInkColors.DarkHighContrast.background,
                    onBackground = EInkColors.DarkHighContrast.onBackground,
                    surface = EInkColors.DarkHighContrast.surface,
                    onSurface = EInkColors.DarkHighContrast.onSurface,
                    surfaceVariant = EInkColors.DarkHighContrast.surfaceVariant,
                    onSurfaceVariant = EInkColors.DarkHighContrast.onSurfaceVariant,
                    outline = EInkColors.DarkHighContrast.outline,
                    error = EInkColors.DarkHighContrast.error,
                    onError = EInkColors.DarkHighContrast.onError
                )
            } else {
                EInkColorScheme(
                    primary = EInkColors.HighContrast.primary,
                    onPrimary = EInkColors.HighContrast.onPrimary,
                    primaryContainer = EInkColors.HighContrast.primaryContainer,
                    onPrimaryContainer = EInkColors.HighContrast.onPrimaryContainer,
                    secondary = EInkColors.HighContrast.secondary,
                    onSecondary = EInkColors.HighContrast.onSecondary,
                    secondaryContainer = EInkColors.HighContrast.secondaryContainer,
                    onSecondaryContainer = EInkColors.HighContrast.onSecondaryContainer,
                    background = EInkColors.HighContrast.background,
                    onBackground = EInkColors.HighContrast.onBackground,
                    surface = EInkColors.HighContrast.surface,
                    onSurface = EInkColors.HighContrast.onSurface,
                    surfaceVariant = EInkColors.HighContrast.surfaceVariant,
                    onSurfaceVariant = EInkColors.HighContrast.onSurfaceVariant,
                    outline = EInkColors.HighContrast.outline,
                    error = EInkColors.HighContrast.error,
                    onError = EInkColors.HighContrast.onError
                )
            }
        }
        EInkColorVariant.Grayscale -> {
            if (darkTheme) {
                EInkColorScheme(
                    primary = EInkColors.DarkGrayscale.primary,
                    onPrimary = EInkColors.DarkGrayscale.onPrimary,
                    primaryContainer = EInkColors.DarkGrayscale.primaryContainer,
                    onPrimaryContainer = EInkColors.DarkGrayscale.onPrimaryContainer,
                    secondary = EInkColors.DarkGrayscale.secondary,
                    onSecondary = EInkColors.DarkGrayscale.onSecondary,
                    secondaryContainer = EInkColors.DarkGrayscale.secondaryContainer,
                    onSecondaryContainer = EInkColors.DarkGrayscale.onSecondaryContainer,
                    background = EInkColors.DarkGrayscale.background,
                    onBackground = EInkColors.DarkGrayscale.onBackground,
                    surface = EInkColors.DarkGrayscale.surface,
                    onSurface = EInkColors.DarkGrayscale.onSurface,
                    surfaceVariant = EInkColors.DarkGrayscale.surfaceVariant,
                    onSurfaceVariant = EInkColors.DarkGrayscale.onSurfaceVariant,
                    outline = EInkColors.DarkGrayscale.outline,
                    error = EInkColors.DarkGrayscale.error,
                    onError = EInkColors.DarkGrayscale.onError
                )
            } else {
                EInkColorScheme(
                    primary = EInkColors.Grayscale.primary,
                    onPrimary = EInkColors.Grayscale.onPrimary,
                    primaryContainer = EInkColors.Grayscale.primaryContainer,
                    onPrimaryContainer = EInkColors.Grayscale.onPrimaryContainer,
                    secondary = EInkColors.Grayscale.secondary,
                    onSecondary = EInkColors.Grayscale.onSecondary,
                    secondaryContainer = EInkColors.Grayscale.secondaryContainer,
                    onSecondaryContainer = EInkColors.Grayscale.onSecondaryContainer,
                    background = EInkColors.Grayscale.background,
                    onBackground = EInkColors.Grayscale.onBackground,
                    surface = EInkColors.Grayscale.surface,
                    onSurface = EInkColors.Grayscale.onSurface,
                    surfaceVariant = EInkColors.Grayscale.surfaceVariant,
                    onSurfaceVariant = EInkColors.Grayscale.onSurfaceVariant,
                    outline = EInkColors.Grayscale.outline,
                    error = EInkColors.Grayscale.error,
                    onError = EInkColors.Grayscale.onError
                )
            }
        }
    }
}

/**
 * Create E-Ink typography system
 */
private fun createTypographySystem(): EInkTypographySystem {
    return EInkTypographySystem(
        displayLarge = EInkTypography.displayLarge,
        displayMedium = EInkTypography.displayMedium,
        displaySmall = EInkTypography.displaySmall,
        headlineLarge = EInkTypography.headlineLarge,
        headlineMedium = EInkTypography.headlineMedium,
        headlineSmall = EInkTypography.headlineSmall,
        titleLarge = EInkTypography.titleLarge,
        titleMedium = EInkTypography.titleMedium,
        titleSmall = EInkTypography.titleSmall,
        bodyLarge = EInkTypography.bodyLarge,
        bodyMedium = EInkTypography.bodyMedium,
        bodySmall = EInkTypography.bodySmall,
        labelLarge = EInkTypography.labelLarge,
        labelMedium = EInkTypography.labelMedium,
        labelSmall = EInkTypography.labelSmall
    )
}

/**
 * Main E-Ink theme composable that provides theming for E-Ink optimized applications.
 * 
 * @param colorVariant The color scheme variant to use (HighContrast or Grayscale)
 * @param darkTheme Whether to use dark theme colors
 * @param content The content to theme
 */
@Composable
fun EInkTheme(
    colorVariant: EInkColorVariant = EInkColorVariant.HighContrast,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = createColorScheme(colorVariant, darkTheme)
    val typography = createTypographySystem()
    
    // Provide theme values and disable ripple effects globally
    CompositionLocalProvider(
        LocalEInkColorScheme provides colorScheme,
        LocalEInkTypography provides typography,
        LocalRippleTheme provides NoRippleTheme,
        LocalIndication provides null, // Also disable indication
        content = content
    )
}

// CompositionLocals for accessing theme values
@Composable
fun eInkColorScheme(): EInkColorScheme = LocalEInkColorScheme.current

@Composable
fun eInkTypography(): EInkTypographySystem = LocalEInkTypography.current

@Composable
fun eInkContentColor(): Color = LocalEInkContentColor.current

// Internal CompositionLocals
internal val LocalEInkColorScheme = androidx.compose.runtime.compositionLocalOf<EInkColorScheme> {
    error("No EInkColorScheme provided")
}

internal val LocalEInkTypography = androidx.compose.runtime.compositionLocalOf<EInkTypographySystem> {
    error("No EInkTypography provided")
}

internal val LocalEInkContentColor = androidx.compose.runtime.compositionLocalOf<Color> {
    Color.Unspecified
}