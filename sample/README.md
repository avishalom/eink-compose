# EInk Compose Sample App

This sample app demonstrates all components of the EInk Compose library optimized for E-Ink displays.

## Features

- **Complete Component Showcase**: Demonstrates all 15+ EInk Compose components
- **Paginated Navigation**: Uses library's pagination instead of scrolling
- **Theme Switching**: Toggle between High Contrast and Grayscale variants
- **E-Ink Optimized**: Zero animations, high contrast, border-based design

## App Structure

- **MainActivity.kt**: Entry point with EInkTheme setup
- **ComponentShowcase.kt**: Main showcase with 6 demonstration pages:
  1. Typography components
  2. Button variants  
  3. Card components
  4. Text input fields
  5. Layout components
  6. Theme variants

## Building & Running

1. Ensure Android 11+ (API 30) target device
2. Build with `./gradlew :sample:assembleDebug`
3. Install on BOOX device for best experience
4. Navigate using swipe gestures (no scrolling)

## Testing on E-Ink

- Verify no visual artifacts or ghosting
- Test touch targets meet 36dp/48dp minimums
- Confirm text readability at 14sp minimum
- Check instant state changes without animations

Perfect for demonstrating EInk Compose library capabilities!