package com.eink.compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.eink.compose.modifiers.staticClickable
import com.eink.compose.theme.eInkColorScheme
import com.eink.compose.utils.EInkConstants

/**
 * E-Ink optimized card component with zero elevation and border-based visual separation.
 * Uses high contrast borders instead of shadows for defining boundaries.
 * Follows the flat design principle for E-Ink displays.
 * 
 * @param modifier Modifier for the card
 * @param onClick Optional click handler (makes the card clickable)
 * @param enabled Whether the card is enabled (only relevant if onClick is provided)
 * @param colors Card colors (defaults to surface colors from theme)
 * @param border Border for the card (defaults to outline color)
 * @param shape Shape of the card
 * @param contentPadding Padding around the card content
 * @param content The content of the card
 */
@Composable
fun EInkCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: EInkCardColors = EInkCardDefaults.colors(),
    border: BorderStroke = EInkCardDefaults.border(),
    shape: Shape = RoundedCornerShape(EInkConstants.CornerRadius.MEDIUM),
    contentPadding: PaddingValues = EInkCardDefaults.contentPadding(),
    content: @Composable ColumnScope.() -> Unit
) {
    val backgroundColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val borderColor = if (enabled) border.brush else EInkCardDefaults.disabledBorder().brush
    
    val cardModifier = modifier
        .background(
            color = backgroundColor,
            shape = shape
        )
        .border(
            border = border.copy(brush = borderColor),
            shape = shape
        )
        .then(
            if (onClick != null) {
                Modifier.staticClickable(
                    enabled = enabled,
                    onClick = onClick
                )
            } else {
                Modifier
            }
        )
        .padding(contentPadding)
    
    Column(
        modifier = cardModifier,
        content = content
    )
}

/**
 * Elevated card variant (for hierarchical content)
 * Note: Uses stronger border instead of elevation for E-Ink compatibility
 */
@Composable
fun EInkElevatedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: EInkCardColors = EInkCardDefaults.elevatedColors(),
    border: BorderStroke = EInkCardDefaults.elevatedBorder(),
    shape: Shape = RoundedCornerShape(EInkConstants.CornerRadius.MEDIUM),
    contentPadding: PaddingValues = EInkCardDefaults.contentPadding(),
    content: @Composable ColumnScope.() -> Unit
) {
    EInkCard(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        border = border,
        shape = shape,
        contentPadding = contentPadding,
        content = content
    )
}

/**
 * Outlined card variant with transparent background
 */
@Composable
fun EInkOutlinedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: EInkCardColors = EInkCardDefaults.outlinedColors(),
    border: BorderStroke = EInkCardDefaults.outlinedBorder(),
    shape: Shape = RoundedCornerShape(EInkConstants.CornerRadius.MEDIUM),
    contentPadding: PaddingValues = EInkCardDefaults.contentPadding(),
    content: @Composable ColumnScope.() -> Unit
) {
    EInkCard(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        border = border,
        shape = shape,
        contentPadding = contentPadding,
        content = content
    )
}

/**
 * Card colors for E-Ink themes
 */
data class EInkCardColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
)

/**
 * Default values for E-Ink cards
 */
object EInkCardDefaults {
    
    @Composable
    fun colors(): EInkCardColors {
        val colors = eInkColorScheme()
        return EInkCardColors(
            containerColor = colors.surface,
            contentColor = colors.onSurface,
            disabledContainerColor = colors.surfaceVariant,
            disabledContentColor = colors.onSurfaceVariant
        )
    }
    
    @Composable
    fun elevatedColors(): EInkCardColors {
        val colors = eInkColorScheme()
        return EInkCardColors(
            containerColor = colors.primaryContainer,
            contentColor = colors.onPrimaryContainer,
            disabledContainerColor = colors.surfaceVariant,
            disabledContentColor = colors.onSurfaceVariant
        )
    }
    
    @Composable
    fun outlinedColors(): EInkCardColors {
        val colors = eInkColorScheme()
        return EInkCardColors(
            containerColor = Color.Transparent,
            contentColor = colors.onSurface,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = colors.onSurfaceVariant
        )
    }
    
    @Composable
    fun border() = BorderStroke(
        width = EInkConstants.Borders.THIN,
        color = eInkColorScheme().outline
    )
    
    @Composable
    fun elevatedBorder() = BorderStroke(
        width = EInkConstants.Borders.MEDIUM,
        color = eInkColorScheme().primary
    )
    
    @Composable
    fun outlinedBorder() = BorderStroke(
        width = EInkConstants.Borders.THIN,
        color = eInkColorScheme().outline
    )
    
    @Composable
    fun disabledBorder() = BorderStroke(
        width = EInkConstants.Borders.THIN,
        color = eInkColorScheme().onSurfaceVariant
    )
    
    fun contentPadding() = PaddingValues(EInkConstants.Spacing.MEDIUM)
}