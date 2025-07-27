package com.eink.sample

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eink.compose.components.*
import com.eink.compose.theme.EInkColorVariant
import com.eink.compose.theme.EInkTheme
import com.eink.compose.utils.EInkConstants

/**
 * Main showcase composable demonstrating all EInk Compose components.
 * Organized in a paginated view perfect for E-Ink devices.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComponentShowcase(
    modifier: Modifier = Modifier
) {
    var currentTheme by remember { mutableStateOf(EInkColorVariant.HighContrast) }
    
    EInkTheme(colorVariant = currentTheme) {
        val showcaseItems = remember {
            listOf(
                "Typography",
                "Buttons", 
                "Cards",
                "Text Fields",
                "Layout Components",
                "Theme Variants"
            )
        }
        
        SimplePaginatedList(
            items = showcaseItems,
            itemsPerPage = 1, // One showcase per page
            modifier = modifier,
            showPageIndicator = true,
            indicatorStyle = PageIndicatorStyle.Numbers
        ) { item, index ->
            EInkContainer(
                modifier = Modifier.fillMaxSize(),
                padding = PaddingValues(EInkConstants.Spacing.MEDIUM)
            ) {
                when (item) {
                    "Typography" -> TypographyShowcase()
                    "Buttons" -> ButtonShowcase()
                    "Cards" -> CardShowcase()
                    "Text Fields" -> TextFieldShowcase()
                    "Layout Components" -> LayoutShowcase()
                    "Theme Variants" -> ThemeShowcase(
                        currentTheme = currentTheme,
                        onThemeChange = { currentTheme = it }
                    )
                }
            }
        }
    }
}

@Composable
private fun TypographyShowcase() {
    EInkSection(title = "Typography Components") {
        EInkText(
            text = "Display Large", 
            style = com.eink.compose.theme.eInkTypography().displayLarge
        )
        EInkHeadline("Headline Medium")
        EInkTitle("Title Large") 
        EInkBodyText("Body text automatically enforces 14sp minimum size for E-Ink readability.")
        EInkLabel("Label text is always bold for maximum contrast")
        
        EInkVerticalSpacer(EInkSpacerSize.Medium)
        
        EInkCard {
            EInkTitle("Typography Features")
            EInkBodyText("• Minimum 14sp font size enforcement")
            EInkBodyText("• Bold weights preferred for E-Ink displays")
            EInkBodyText("• High contrast color mapping")
            EInkBodyText("• No font animations or transitions")
        }
    }
}

@Composable
private fun ButtonShowcase() {
    EInkSection(title = "Button Components") {
        EInkColumn {
            EInkButton(onClick = { }) {
                EInkText("Primary Button")
            }
            
            EInkOutlinedButton(onClick = { }) {
                EInkText("Outlined Button")  
            }
            
            EInkTextButton(onClick = { }) {
                EInkText("Text Button")
            }
            
            EInkRow {
                EInkButton(
                    onClick = { },
                    isEdgeButton = true
                ) {
                    EInkText("Edge Button (48dp)")
                }
                
                EInkButton(
                    onClick = { },
                    isEdgeButton = false
                ) {
                    EInkText("Central (36dp)")
                }
            }
        }
        
        EInkCard {
            EInkTitle("Button Features")
            EInkBodyText("• Zero elevation (no shadows)")
            EInkBodyText("• No ripple effects") 
            EInkBodyText("• Touch target compliance (36dp/48dp)")
            EInkBodyText("• Instant visual state changes")
        }
    }
}

@Composable 
private fun CardShowcase() {
    EInkSection(title = "Card Components") {
        EInkRow {
            EInkCard(
                modifier = Modifier.weight(1f)
            ) {
                EInkTitle("Standard Card")
                EInkBodyText("Uses borders instead of shadows for visual separation.")
            }
            
            EInkHorizontalSpacer(EInkSpacerSize.Small)
            
            EInkElevatedCard(
                modifier = Modifier.weight(1f)
            ) {
                EInkTitle("Elevated Card") 
                EInkBodyText("Stronger border for emphasis.")
            }
        }
        
        EInkVerticalSpacer(EInkSpacerSize.Medium)
        
        EInkOutlinedCard(
            onClick = { }
        ) {
            EInkTitle("Clickable Card")
            EInkBodyText("Cards can be interactive without ripple effects. This card responds to clicks with instant feedback.")
        }
    }
}

@Composable
private fun TextFieldShowcase() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("Sample text") }
    
    EInkSection(title = "Text Input Components") {
        EInkTextField(
            value = text1,
            onValueChange = { text1 = it },
            label = "Standard TextField",
            placeholder = "Enter text here..."
        )
        
        EInkOutlinedTextField(
            value = text2, 
            onValueChange = { text2 = it },
            label = "Outlined TextField",
            placeholder = "Transparent background"
        )
        
        EInkTextField(
            value = text3,
            onValueChange = { text3 = it },
            label = "Error State",
            isError = true,
            supportingText = "This field has an error"
        )
        
        EInkCard {
            EInkTitle("TextField Features")
            EInkBodyText("• Static borders (no focus animations)")
            EInkBodyText("• High contrast error states")
            EInkBodyText("• 14sp minimum text size enforcement")
            EInkBodyText("• No ripple or visual feedback")
        }
    }
}

@Composable
private fun LayoutShowcase() {
    EInkSection(title = "Layout Components") {
        EInkCard {
            EInkTitle("EInkRow with Spacers")
            EInkRow {
                EInkBodyText("Left")
                EInkHorizontalSpacer(EInkSpacerSize.Large)
                EInkBodyText("Right")
            }
        }
        
        EInkCard {
            EInkTitle("EInkColumn with Different Spacers")
            EInkColumn {
                EInkBodyText("Item 1")
                EInkVerticalSpacer(EInkSpacerSize.ExtraSmall)
                EInkBodyText("Item 2 (ExtraSmall gap)")
                EInkVerticalSpacer(EInkSpacerSize.Large) 
                EInkBodyText("Item 3 (Large gap)")
            }
        }
        
        EInkCard {
            EInkTitle("Layout Features")
            EInkBodyText("• Generous spacing for touch targets")
            EInkBodyText("• Consistent spacing scale")
            EInkBodyText("• No animations or transitions")
            EInkBodyText("• Pagination instead of scrolling")
        }
    }
}

@Composable
private fun ThemeShowcase(
    currentTheme: EInkColorVariant,
    onThemeChange: (EInkColorVariant) -> Unit
) {
    EInkSection(title = "Theme Variants") {
        EInkCard {
            EInkTitle("Current Theme: ${currentTheme.name}")
            EInkBodyText(
                when (currentTheme) {
                    EInkColorVariant.HighContrast -> "Pure black and white for maximum contrast"
                    EInkColorVariant.Grayscale -> "16-level grayscale for subtle hierarchy"
                }
            )
        }
        
        EInkVerticalSpacer(EInkSpacerSize.Medium)
        
        EInkRow {
            EInkButton(
                onClick = { onThemeChange(EInkColorVariant.HighContrast) }
            ) {
                EInkText("High Contrast")
            }
            
            EInkButton(
                onClick = { onThemeChange(EInkColorVariant.Grayscale) }
            ) {
                EInkText("Grayscale")
            }
        }
        
        EInkCard {
            EInkTitle("Theme Features")
            EInkBodyText("• Two optimized variants")
            EInkBodyText("• Dark/light mode support")
            EInkBodyText("• Global grayscale enforcement")
            EInkBodyText("• No color saturation")
        }
    }
}