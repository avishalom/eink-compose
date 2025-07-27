package com.eink.compose.modifiers

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * A no-operation InteractionSource that doesn't emit any interactions.
 * This effectively disables all interaction feedback including ripples.
 * Use this for components that don't respect the global ripple configuration.
 */
object NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {
        // No-op: swallow all interactions
    }
    override fun tryEmit(interaction: Interaction): Boolean = true
}

/**
 * Creates a remembered NoRippleInteractionSource.
 * This is useful when you need a fresh instance for each composable.
 * Creates a new instance to avoid shared state issues.
 */
@Composable
fun rememberNoRippleInteractionSource(): MutableInteractionSource {
    return remember { 
        object : MutableInteractionSource {
            override val interactions: Flow<Interaction> = emptyFlow()
            override suspend fun emit(interaction: Interaction) {
                // No-op: swallow all interactions
            }
            override fun tryEmit(interaction: Interaction): Boolean = true
        }
    }
}

/**
 * An Indication that provides no visual feedback.
 * This can be used as a fallback when the global ripple disabling doesn't work.
 */
object NoIndication : Indication {
    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return NoIndicationInstance
    }
}

private object NoIndicationInstance : IndicationInstance {
    override fun ContentDrawScope.drawIndication() {
        // No visual indication - just draw content normally
        drawContent()
    }
}

/**
 * Creates a remembered NoIndication.
 * This is useful when you need to explicitly provide an indication parameter.
 */
@Composable
fun rememberNoIndication(): Indication {
    return remember { NoIndication }
}