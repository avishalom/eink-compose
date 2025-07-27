package com.eink.compose.modifiers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role

/**
 * A clickable modifier that provides no visual feedback (no ripple, no indication).
 * This is the E-Ink equivalent of the standard clickable modifier, ensuring
 * that clicks are handled without any animation or visual effect that would
 * cause unwanted screen updates.
 * 
 * @param enabled Controls the enabled state of the element
 * @param onClickLabel Optional label for accessibility
 * @param role Optional role for accessibility
 * @param onClick The callback to invoke when clicked
 */
fun Modifier.staticClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = composed {
    val interactionSource = remember { NoRippleInteractionSource }
    
    clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        interactionSource = interactionSource,
        indication = null, // No visual indication
        onClick = onClick
    )
}

/**
 * A version of staticClickable that allows providing a custom InteractionSource.
 * This is useful when you need to observe interactions without providing visual feedback.
 * 
 * @param interactionSource The InteractionSource to use
 * @param enabled Controls the enabled state of the element
 * @param onClickLabel Optional label for accessibility
 * @param role Optional role for accessibility
 * @param onClick The callback to invoke when clicked
 */
fun Modifier.staticClickable(
    interactionSource: MutableInteractionSource,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = clickable(
    enabled = enabled,
    onClickLabel = onClickLabel,
    role = role,
    interactionSource = interactionSource,
    indication = null, // No visual indication
    onClick = onClick
)