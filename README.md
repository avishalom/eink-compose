# EInk Compose 📚

A Jetpack Compose library specifically optimized for E-Ink displays, particularly BOOX devices. This library provides a complete set of components following the "static-first" design philosophy, ensuring perfect rendering on electrophoretic displays.

## ✨ Features

- **Zero Animation Design**: All components are built without animations to prevent ghosting on E-Ink displays
- **High Contrast Theming**: Two theme variants (High Contrast & 16-level Grayscale) optimized for E-Ink readability
- **E-Ink Native Components**: 15 essential components designed specifically for E-Ink constraints
- **Pagination Over Scrolling**: HorizontalPager-based components replace scrolling patterns
- **Minimum Touch Targets**: All interactive elements meet accessibility and usability guidelines
- **Typography Enforcement**: Automatic 14sp minimum font size compliance
- **Border-Based Design**: Visual separation through borders instead of shadows/elevation

## 🚀 Installation

### Option 1: Local Library Integration (Recommended for Development)

Since this library is not yet published to a remote repository, you'll need to include it as a local module in your project:

#### 1.1: Clone the Repository
```bash
# Clone into your project root or as a submodule
git clone https://github.com/avishalom/eink-compose.git
# OR add as git submodule
git submodule add https://github.com/avishalom/eink-compose.git
```

#### 1.2: Configure Project Structure
```kotlin
// In your project's settings.gradle.kts
include(":app")
include(":eink-compose:library")  // Add this line

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
```

#### 1.3: Add Dependency
```kotlin
// In your app module's build.gradle.kts
dependencies {
    implementation(project(":eink-compose:library"))
    
    // Ensure you have the required Compose dependencies
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.activity:activity-compose:1.8.0")
}
```

### Option 2: Manual Library Copy (Alternative)

If you prefer to copy the library code directly into your project:

#### 2.1: Copy Library Module
```bash
# Copy the library folder to your project
cp -r eink-compose/library ./eink-compose-lib
```

#### 2.2: Update settings.gradle.kts
```kotlin
include(":app")
include(":eink-compose-lib")  // Your renamed library module
```

#### 2.3: Add Dependency
```kotlin
// In your app's build.gradle.kts
dependencies {
    implementation(project(":eink-compose-lib"))
}
```

### Option 3: Future Maven/Gradle Repository (Coming Soon)

Once published to Maven Central or a custom repository, installation will be simplified to:

```kotlin
// This will be available in future versions
dependencies {
    implementation("com.eink.compose:library:1.0.0")
}
```

> **Note**: The library will be published to a Gradle repository in a future release. For now, local integration is the recommended approach.

### Step 2: Update Your Build Configuration

Ensure your project targets the appropriate Android version:

```kotlin
android {
    compileSdk = 34
    
    defaultConfig {
        minSdk = 30  // Android 11 for BOOX compatibility
        targetSdk = 34
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    buildFeatures {
        compose = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}
```

## 📖 How to Use

### Basic Setup

Wrap your app content with the `EInkTheme`:

```kotlin
import com.eink.compose.theme.EInkTheme
import com.eink.compose.theme.EInkColorVariant

@Composable
fun MyApp() {
    EInkTheme(
        colorVariant = EInkColorVariant.HighContrast, // or EInkColorVariant.Grayscale
        darkTheme = isSystemInDarkTheme()
    ) {
        // Your app content here
        MyMainScreen()
    }
}
```

### Using Components

#### Text Components

```kotlin
import com.eink.compose.components.*

@Composable
fun TextExample() {
    EInkColumn {
        EInkHeadline("Main Title")
        EInkTitle("Section Title")
        EInkBodyText("This is body text that will automatically be at least 14sp")
        EInkLabel("Label Text")
    }
}
```

#### Button Components

```kotlin
@Composable
fun ButtonExample() {
    EInkRow {
        EInkButton(onClick = { /* action */ }) {
            EInkText("Primary Button")
        }
        
        EInkOutlinedButton(onClick = { /* action */ }) {
            EInkText("Outlined Button")
        }
        
        EInkTextButton(onClick = { /* action */ }) {
            EInkText("Text Button")
        }
    }
}
```

#### Card Components

```kotlin
@Composable
fun CardExample() {
    EInkCard {
        EInkTitle("Card Title")
        EInkBodyText("Card content goes here. Cards use borders instead of shadows.")
    }
    
    // Clickable card
    EInkCard(onClick = { /* handle click */ }) {
        EInkTitle("Clickable Card")
        EInkBodyText("This card responds to clicks")
    }
}
```

#### Paginated Lists (Instead of Scrolling)

```kotlin
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaginatedListExample() {
    val items = remember { (1..100).map { "Item $it" } }
    
    SimplePaginatedList(
        items = items,
        itemsPerPage = 10,
        showPageIndicator = true,
        indicatorStyle = PageIndicatorStyle.Numbers
    ) { item, index ->
        EInkCard {
            EInkText(item)
        }
    }
}
```

#### Text Input

```kotlin
@Composable
fun TextFieldExample() {
    var text by remember { mutableStateOf("") }
    
    EInkColumn {
        EInkTextField(
            value = text,
            onValueChange = { text = it },
            label = "Label",
            placeholder = "Enter text here..."
        )
        
        EInkOutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = "Outlined TextField"
        )
    }
}
```

### Layout Components

```kotlin
@Composable
fun LayoutExample() {
    EInkContainer {
        EInkSection(title = "Settings") {
            EInkText("Option 1")
            EInkText("Option 2")
        }
        
        EInkVerticalSpacer(EInkSpacerSize.Large)
        
        EInkRow {
            EInkText("Left")
            EInkHorizontalSpacer(EInkSpacerSize.Medium)
            EInkText("Right")
        }
    }
}
```

### Advanced: Custom Components

When creating custom components, use the E-Ink utilities:

```kotlin
import com.eink.compose.modifiers.staticClickable
import com.eink.compose.modifiers.grayscale
import com.eink.compose.theme.eInkColorScheme
import com.eink.compose.theme.eInkTypography

@Composable
fun CustomComponent() {
    Box(
        modifier = Modifier
            .background(eInkColorScheme().surface)
            .border(1.dp, eInkColorScheme().outline)
            .staticClickable { /* no ripple click */ }
            .grayscale() // Force grayscale on any content
    ) {
        Text(
            text = "Custom Component",
            style = eInkTypography().bodyLarge,
            color = eInkColorScheme().onSurface
        )
    }
}
```

## 📋 Component Reference

### Foundation Components
- `EInkText`, `EInkHeadline`, `EInkTitle`, `EInkBodyText`, `EInkLabel`
- `EInkButton`, `EInkOutlinedButton`, `EInkTextButton`  
- `EInkCard`, `EInkElevatedCard`, `EInkOutlinedCard`
- `EInkTextField`, `EInkOutlinedTextField`

### Layout Components  
- `EInkRow`, `EInkColumn`, `EInkBox`
- `EInkSpacer`, `EInkHorizontalSpacer`, `EInkVerticalSpacer`
- `EInkSurface`, `EInkContainer`, `EInkSection`
- `PaginatedList`, `SimplePaginatedList`, `PaginatedGrid`

### Modifiers
- `Modifier.staticClickable()` - Click handling without ripple effects
- `Modifier.grayscale()` - Force grayscale rendering
- `NoRippleInteractionSource` - Disable interaction feedback

## 🎨 Theme Customization

### Color Variants

```kotlin
// High contrast (pure black and white only)
EInkTheme(colorVariant = EInkColorVariant.HighContrast) { ... }

// Grayscale (16-level grayscale palette)  
EInkTheme(colorVariant = EInkColorVariant.Grayscale) { ... }
```

### Accessing Theme Values

```kotlin
@Composable
fun ThemedComponent() {
    val colors = eInkColorScheme()
    val typography = eInkTypography()
    
    Text(
        text = "Themed text",
        color = colors.primary,
        style = typography.headlineMedium
    )
}
```

## 🧪 Testing on E-Ink Devices

1. **Deploy to Device**: Install the sample app on your BOOX device
2. **Check Component Showcase**: Review all components in the included sample
3. **Verify Readability**: Ensure text meets 14sp minimum and is readable
4. **Test Interactions**: Confirm buttons and clicks work without visual artifacts
5. **Validate Performance**: Check for unwanted screen flashes or ghosting

## 🔄 Migration from Material Design

Key differences when migrating from Material Design:

| Material Design | EInk Compose |
|----------------|--------------|
| `LazyColumn` → | `SimplePaginatedList` |
| `AnimatedVisibility` → | `if (visible) { ... }` |
| `Button` → | `EInkButton` |
| `Card` → | `EInkCard` |
| `TextField` → | `EInkTextField` |
| `MaterialTheme` → | `EInkTheme` |
| Shadows/Elevation → | Borders |
| Ripple effects → | Static clicks |

## 🛠️ Requirements

- **Android Version**: API 30+ (Android 11)
- **Jetpack Compose**: BOM 2023.10.01+
- **Target Devices**: BOOX E-Ink devices (tested compatible)
- **Kotlin**: 1.9.10+

## 📄 Future Enhancements

See [FUTURE_ENHANCEMENTS.md](FUTURE_ENHANCEMENTS.md) for planned features and component additions.

## 📜 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**Built for E-Ink Excellence** 🖤🤍