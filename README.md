# ConnectTag Lib About

[![JitPack](https://jitpack.io/v/connecttag/connecttaglibabout.svg)](https://jitpack.io/#connecttag/connecttaglibabout)

UI Library for ConnectTag Android applications, providing "About App" and "About Developer" screens, dialogs, bottom sheets, and social networks presentation components.

## Features

- **About App Screen (`BaseAboutAppScreen`):** Full modern screen displaying application identity, version, package name, description, websites, actions, and social media channels.
- **About App Presentation (`AboutAppPresentation` / `AboutAppDialog`):** Multi-mode presentation supporting Dialog, Bottom Sheet, or FullScreen modes with rich customization.
- **About Developer Screen (`ConnectTagInfoScreen`):** Ready-to-use branded screen showcasing ConnectTag developer profile, official website, contact email, code repositories (GitHub, GitLab), social media channels, and company apps catalog.
- **Social Networks Catalog:** Comprehensive pre-configured catalog for 15+ social networks and stores (WhatsApp, Telegram, Facebook, X, Instagram, YouTube, LinkedIn, Google Play, etc.) with custom vector icons and URI scheme resolution.
- **Multi-language Support:** Complete built-in localization for Arabic and English.
- **Jetpack Compose & Material 3:** Built purely with Jetpack Compose and Material 3 design principles.

## Installation

### 1. Add JitPack to your project

Add it to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. Add the dependency

Add it to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.connecttag:connecttaglibabout:1.0.0")
}
```

## Usage

### 1. Displaying the "About App" Screen

```kotlin
import org.connecttag.lib.kotlin.about.BaseAboutAppScreen
import org.connecttag.lib.kotlin.about.AboutAppSocialLinkData

@Composable
fun MyAboutScreen(onBack: () -> Unit, onUrlClick: (String) -> Unit) {
    BaseAboutAppScreen(
        appName = "My Application",
        versionText = "v1.0.0 (100)",
        description = "My application description goes here.",
        appIcon = R.mipmap.ic_launcher,
        onBack = onBack,
        onUrlClick = onUrlClick,
        developerAction = {
            // Navigate to Developer screen
        }
    )
}
```

### 2. Displaying the "About Developer" Screen

```kotlin
import org.connecttag.lib.kotlin.about.ConnectTagInfoScreen

@Composable
fun MyDeveloperScreen(onBack: () -> Unit, onUrlClick: (String) -> Unit) {
    ConnectTagInfoScreen(
        onBack = onBack,
        onUrlClick = onUrlClick
    )
}
```

### 3. Displaying About App as Dialog or BottomSheet

```kotlin
import org.connecttag.lib.kotlin.about.compose.AboutAppDialog
import org.connecttag.lib.kotlin.about.AboutAppProfile
import org.connecttag.lib.kotlin.about.AboutAppDialogLabels

@Composable
fun ShowAboutDialog(profile: AboutAppProfile, onDismiss: () -> Unit, onUrlClick: (String) -> Unit) {
    AboutAppDialog(
        profile = profile,
        title = "About",
        labels = AboutAppDialogLabels(
            infoContentDescription = "Info",
            closeContentDescription = "Close",
            websiteTitle = "Website"
        ),
        onDismissRequest = onDismiss,
        onWebsiteClick = onUrlClick,
        onSocialLinkClick = { link -> onUrlClick(link.value) }
    )
}
```

## License

```
Copyright 2026 ConnectTag

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
