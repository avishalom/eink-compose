package com.eink.compose.utils

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Constants for E-Ink optimized UI elements following BOOX guidelines
 */
object EInkConstants {
    
    /**
     * Minimum touch targets following guideline #6
     */
    object TouchTargets {
        /** Minimum size for buttons in central area (36dp x 36dp) */
        val CENTRAL_BUTTON_MIN_SIZE = 36.dp
        
        /** Minimum size for buttons in edge area (48dp x 48dp) */
        val EDGE_BUTTON_MIN_SIZE = 48.dp
        
        /** Default minimum touch target size (48dp for accessibility) */
        val DEFAULT_MIN_SIZE = 48.dp
    }
    
    /**
     * Typography constraints following guideline #5
     */
    object Typography {
        /** Absolute minimum font size (14sp) */
        val MIN_FONT_SIZE = 14.sp
        
        /** Recommended minimum for body text */
        val BODY_MIN_SIZE = 16.sp
        
        /** Recommended size for headlines */
        val HEADLINE_SIZE = 24.sp
    }
    
    /**
     * Standard spacing values for consistent layout
     */
    object Spacing {
        val EXTRA_SMALL = 4.dp
        val SMALL = 8.dp
        val MEDIUM = 16.dp
        val LARGE = 24.dp
        val EXTRA_LARGE = 32.dp
    }
    
    /**
     * Border widths for visual separation without shadows
     */
    object Borders {
        val THIN = 1.dp
        val MEDIUM = 2.dp
        val THICK = 3.dp
    }
    
    /**
     * Corner radius values (minimal for E-Ink)
     */
    object CornerRadius {
        val SMALL = 4.dp
        val MEDIUM = 8.dp
        val LARGE = 12.dp
    }
}