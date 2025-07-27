package com.eink.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RectangleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eink.compose.theme.eInkColorScheme
import com.eink.compose.theme.eInkTypography
import com.eink.compose.utils.EInkConstants

/**
 * E-Ink optimized Row component with generous spacing defaults.
 * Provides consistent spacing that works well for touch targets and readability.
 */
@Composable
fun EInkRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(EInkConstants.Spacing.MEDIUM),
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = content
    )
}

/**
 * E-Ink optimized Column component with generous spacing defaults.
 * Provides consistent spacing that works well for touch targets and readability.
 */
@Composable
fun EInkColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(EInkConstants.Spacing.MEDIUM),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}

/**
 * E-Ink optimized Box component for flexible layouts
 */
@Composable
fun EInkBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
        content = content
    )
}

/**
 * Spacer component with predefined E-Ink appropriate sizes
 */
@Composable
fun EInkSpacer(
    size: EInkSpacerSize = EInkSpacerSize.Medium,
    modifier: Modifier = Modifier
) {
    val spacerSize = when (size) {
        EInkSpacerSize.ExtraSmall -> EInkConstants.Spacing.EXTRA_SMALL
        EInkSpacerSize.Small -> EInkConstants.Spacing.SMALL
        EInkSpacerSize.Medium -> EInkConstants.Spacing.MEDIUM
        EInkSpacerSize.Large -> EInkConstants.Spacing.LARGE
        EInkSpacerSize.ExtraLarge -> EInkConstants.Spacing.EXTRA_LARGE
    }
    
    Spacer(modifier = modifier.size(spacerSize))
}

/**
 * Horizontal spacer with predefined sizes
 */
@Composable
fun EInkHorizontalSpacer(
    size: EInkSpacerSize = EInkSpacerSize.Medium,
    modifier: Modifier = Modifier
) {
    val spacerSize = when (size) {
        EInkSpacerSize.ExtraSmall -> EInkConstants.Spacing.EXTRA_SMALL
        EInkSpacerSize.Small -> EInkConstants.Spacing.SMALL
        EInkSpacerSize.Medium -> EInkConstants.Spacing.MEDIUM
        EInkSpacerSize.Large -> EInkConstants.Spacing.LARGE
        EInkSpacerSize.ExtraLarge -> EInkConstants.Spacing.EXTRA_LARGE
    }
    
    Spacer(modifier = modifier.width(spacerSize))
}

/**
 * Vertical spacer with predefined sizes
 */
@Composable
fun EInkVerticalSpacer(
    size: EInkSpacerSize = EInkSpacerSize.Medium,
    modifier: Modifier = Modifier
) {
    val spacerSize = when (size) {
        EInkSpacerSize.ExtraSmall -> EInkConstants.Spacing.EXTRA_SMALL
        EInkSpacerSize.Small -> EInkConstants.Spacing.SMALL
        EInkSpacerSize.Medium -> EInkConstants.Spacing.MEDIUM
        EInkSpacerSize.Large -> EInkConstants.Spacing.LARGE
        EInkSpacerSize.ExtraLarge -> EInkConstants.Spacing.EXTRA_LARGE
    }
    
    Spacer(modifier = modifier.height(spacerSize))
}

/**
 * Predefined spacer sizes for consistency
 */
enum class EInkSpacerSize {
    ExtraSmall,
    Small,
    Medium,
    Large,
    ExtraLarge
}

/**
 * Surface component for E-Ink layouts with proper theming
 */
@Composable
fun EInkSurface(
    modifier: Modifier = Modifier,
    color: Color = eInkColorScheme().surface,
    contentColor: Color = eInkColorScheme().onSurface,
    border: androidx.compose.foundation.BorderStroke? = null,
    shape: Shape = RectangleShape,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(color, shape)
            .then(
                if (border != null) {
                    Modifier.border(border, shape)
                } else {
                    Modifier
                }
            )
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            content = content
        )
    }
}

/**
 * Container component with E-Ink appropriate padding and spacing
 */
@Composable
fun EInkContainer(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(EInkConstants.Spacing.MEDIUM),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.padding(padding),
        content = { content() }
    )
}

/**
 * Section component for grouping related content with a title
 */
@Composable
fun EInkSection(
    title: String,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = eInkTypography().titleMedium,
    spacing: Dp = EInkConstants.Spacing.MEDIUM,
    content: @Composable ColumnScope.() -> Unit
) {
    EInkColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        EInkText(
            text = title,
            style = titleStyle,
            modifier = Modifier.padding(bottom = EInkConstants.Spacing.SMALL)
        )
        content()
    }
}