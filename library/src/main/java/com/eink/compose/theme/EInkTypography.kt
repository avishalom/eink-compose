package com.eink.compose.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * E-Ink optimized typography following the guidelines:
 * - Minimum font size of 14sp
 * - Bold fonts preferred for better contrast and legibility
 * - Clear hierarchy without relying on color
 */
object EInkTypography {
    
    /**
     * Display styles for headers and prominent text
     */
    val displayLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    )
    
    val displayMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    )
    
    val displaySmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    )
    
    /**
     * Headline styles for section headers
     */
    val headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    )
    
    val headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    )
    
    val headlineSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    )
    
    /**
     * Title styles for component headers
     */
    val titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
    
    val titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    )
    
    val titleSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp, // Minimum allowed size
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )
    
    /**
     * Body styles for main content
     * Note: Normal weight may be acceptable for body text if tested on device
     */
    val bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    
    val bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp, // Minimum allowed size
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    )
    
    val bodySmall = TextStyle(
        fontWeight = FontWeight.Medium, // Slightly bolder for small text
        fontSize = 14.sp, // Cannot go below minimum
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    )
    
    /**
     * Label styles for buttons and interactive elements
     */
    val labelLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp, // Minimum allowed size
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )
    
    val labelMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp, // Cannot go below minimum, adjusted from 12sp
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    
    val labelSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp, // Cannot go below minimum, adjusted from 11sp
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
}