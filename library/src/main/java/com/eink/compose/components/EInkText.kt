package com.eink.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.eink.compose.theme.eInkColorScheme
import com.eink.compose.theme.eInkTypography
import com.eink.compose.utils.EInkConstants

/**
 * E-Ink optimized text component that enforces minimum font sizes and uses
 * high contrast colors from the theme. All text styles are guaranteed to be
 * at least 14sp and use appropriate colors for maximum readability.
 * 
 * @param text The text to display
 * @param modifier Modifier for the text
 * @param color The color of the text (defaults to onSurface from theme)
 * @param fontSize The font size (will be enforced to minimum 14sp if smaller)
 * @param fontWeight The font weight
 * @param textAlign Text alignment
 * @param textDecoration Text decoration (underline, strikethrough, etc.)
 * @param overflow How to handle text overflow
 * @param softWrap Whether to allow soft line breaks
 * @param maxLines Maximum number of lines
 * @param style Base text style (fontSize from this will be overridden if below minimum)
 */
@Composable
fun EInkText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = eInkColorScheme().onSurface,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    textDecoration: TextDecoration? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = eInkTypography().bodyMedium
) {
    // Ensure minimum font size compliance with proper TextUnit.Unspecified handling
    val enforcedFontSize = when {
        fontSize != TextUnit.Unspecified && fontSize < EInkConstants.Typography.MIN_FONT_SIZE -> {
            EInkConstants.Typography.MIN_FONT_SIZE
        }
        fontSize != TextUnit.Unspecified -> fontSize
        style.fontSize != TextUnit.Unspecified && style.fontSize < EInkConstants.Typography.MIN_FONT_SIZE -> {
            EInkConstants.Typography.MIN_FONT_SIZE
        }
        style.fontSize != TextUnit.Unspecified -> style.fontSize
        else -> EInkConstants.Typography.MIN_FONT_SIZE // Fallback to minimum if both are unspecified
    }
    
    val finalStyle = style.copy(
        color = color,
        fontSize = enforcedFontSize,
        fontWeight = fontWeight ?: style.fontWeight,
        textAlign = textAlign ?: style.textAlign,
        textDecoration = textDecoration ?: style.textDecoration
    )
    
    BasicText(
        text = text,
        modifier = modifier,
        style = finalStyle,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines
    )
}

/**
 * Predefined text components for common use cases
 */

@Composable
fun EInkHeadline(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = eInkColorScheme().onSurface
) {
    EInkText(
        text = text,
        modifier = modifier,
        color = color,
        style = eInkTypography().headlineMedium
    )
}

@Composable
fun EInkTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = eInkColorScheme().onSurface
) {
    EInkText(
        text = text,
        modifier = modifier,
        color = color,
        style = eInkTypography().titleLarge
    )
}

@Composable
fun EInkBodyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = eInkColorScheme().onSurface
) {
    EInkText(
        text = text,
        modifier = modifier,
        color = color,
        style = eInkTypography().bodyLarge
    )
}

@Composable
fun EInkLabel(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = eInkColorScheme().onSurface
) {
    EInkText(
        text = text,
        modifier = modifier,
        color = color,
        style = eInkTypography().labelLarge
    )
}