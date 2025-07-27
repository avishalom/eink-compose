package com.eink.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.eink.compose.modifiers.grayscale
import com.eink.compose.theme.EInkColorVariant
import com.eink.compose.theme.EInkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EInkTheme(
                colorVariant = EInkColorVariant.HighContrast
            ) {
                // Apply global grayscale filter as safety net
                ComponentShowcase(
                    modifier = Modifier
                        .fillMaxSize()
                        .grayscale()
                )
            }
        }
    }
}