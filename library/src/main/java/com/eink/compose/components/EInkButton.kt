package com.eink.compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.eink.compose.modifiers.staticClickable
import com.eink.compose.theme.eInkColorScheme
import com.eink.compose.utils.EInkConstants

/**
 * E-Ink optimized button component with zero elevation and high contrast styling.
 * Follows guideline #6 for minimum touch targets and provides instant visual feedback
 * through color changes without animations.
 * 
 * @param onClick Callback invoked when button is clicked
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param colors Button colors (defaults to primary colors from theme)
 * @param border Optional border for the button
 * @param shape Shape of the button
 * @param contentPadding Padding around the button content
 * @param isEdgeButton Whether this button is in an edge area (uses larger minimum size)
 * @param content The content of the button (typically text or icon)
 */
@Composable
fun EInkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: EInkButtonColors = EInkButtonDefaults.primaryColors(),
    border: BorderStroke? = null,
    shape: Shape = RoundedCornerShape(EInkConstants.CornerRadius.SMALL),
    contentPadding: PaddingValues = EInkButtonDefaults.contentPadding(),
    isEdgeButton: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    val minSize = if (isEdgeButton) {
        EInkConstants.TouchTargets.EDGE_BUTTON_MIN_SIZE
    } else {
        EInkConstants.TouchTargets.CENTRAL_BUTTON_MIN_SIZE
    }
    
    val backgroundColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    
    Box(
        modifier = modifier
            .defaultMinSize(minWidth = minSize, minHeight = minSize)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .then(
                if (border != null) {
                    Modifier.border(border, shape)
                } else {
                    Modifier
                }
            )
            .staticClickable(
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

/**
 * Outlined button variant with transparent background and border
 */
@Composable
fun EInkOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: EInkButtonColors = EInkButtonDefaults.outlinedColors(),
    border: BorderStroke = EInkButtonDefaults.outlinedBorder(),
    shape: Shape = RoundedCornerShape(EInkConstants.CornerRadius.SMALL),
    contentPadding: PaddingValues = EInkButtonDefaults.contentPadding(),
    isEdgeButton: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    EInkButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        border = border,
        shape = shape,
        contentPadding = contentPadding,
        isEdgeButton = isEdgeButton,
        content = content
    )
}

/**
 * Text button variant with transparent background and no border
 */
@Composable
fun EInkTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: EInkButtonColors = EInkButtonDefaults.textColors(),
    shape: Shape = RoundedCornerShape(EInkConstants.CornerRadius.SMALL),
    contentPadding: PaddingValues = EInkButtonDefaults.textContentPadding(),
    isEdgeButton: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    EInkButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        border = null,
        shape = shape,
        contentPadding = contentPadding,
        isEdgeButton = isEdgeButton,
        content = content
    )
}

/**
 * Button colors for E-Ink themes
 */
data class EInkButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
)

/**
 * Default values for E-Ink buttons
 */
object EInkButtonDefaults {
    
    @Composable
    fun primaryColors(): EInkButtonColors {
        val colors = eInkColorScheme()
        return EInkButtonColors(
            containerColor = colors.primary,
            contentColor = colors.onPrimary,
            disabledContainerColor = colors.surfaceVariant,
            disabledContentColor = colors.onSurfaceVariant
        )
    }
    
    @Composable
    fun outlinedColors(): EInkButtonColors {
        val colors = eInkColorScheme()
        return EInkButtonColors(
            containerColor = Color.Transparent,
            contentColor = colors.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = colors.onSurfaceVariant
        )
    }
    
    @Composable
    fun textColors(): EInkButtonColors {
        val colors = eInkColorScheme()
        return EInkButtonColors(
            containerColor = Color.Transparent,
            contentColor = colors.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = colors.onSurfaceVariant
        )
    }
    
    fun contentPadding() = PaddingValues(
        horizontal = EInkConstants.Spacing.MEDIUM,
        vertical = EInkConstants.Spacing.SMALL
    )
    
    fun textContentPadding() = PaddingValues(
        horizontal = EInkConstants.Spacing.SMALL,
        vertical = EInkConstants.Spacing.EXTRA_SMALL
    )
    
    @Composable
    fun outlinedBorder() = BorderStroke(
        width = EInkConstants.Borders.THIN,
        color = eInkColorScheme().outline
    )
}