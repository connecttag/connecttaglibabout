# ConnectTag Lib About

[![JitPack](https://jitpack.io/v/connecttag/connecttaglibabout.svg)](https://jitpack.io/#connecttag/connecttaglibabout)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

A modular, modern Jetpack Compose library for Android applications providing decoupled **"About App" (حول التطبيق)** and **"About Developer" (حول المطور)** screens, dialogs, bottom sheets, and comprehensive social networks integration.

---

## Architecture & Modular Separation

The library is cleanly divided into separate packages to decouple application metadata from developer/organization profiles:

| Package | Purpose | Key Components |
|---|---|---|
| `org.connecttag.lib.kotlin.about.app` | **About Application** UI and models | `AboutAppScreen`, `AboutAppPresentation`, `AboutAppDialog`, `AboutAppInfo`, `AboutAppRowAction` |
| `org.connecttag.lib.kotlin.about.developer` | **About Developer** UI and profiles | `AboutDeveloperScreen`, `ConnectTagInfoScreen`, `DeveloperProfile`, `ConnectTagDeveloperDefaults` |
| `org.connecttag.lib.kotlin.about.common` | **Shared Components** & utilities | `SocialNetworkCatalog`, `SocialNetworkPlatform`, `SocialLinkResolver`, `resolveSocialIconResource`, `AboutDuotoneIcon` |
| `org.connecttag.lib.kotlin.about` | **Root / Backwards Compatibility** | Forwarding composables and typealiases |

---

## Features

- **Decoupled Architecture:** Application metadata and Developer/Organization presentation can be used together or completely independently.
- **Dedicated About App Screen (`AboutAppScreen`):** Displays application branding, version name, package name, description, official websites, custom support actions, and app social channels.
- **Multi-Mode App Presentation (`AboutAppPresentation` / `AboutAppDialog`):** Render About App as a Dialog, Modal BottomSheet, or FullScreen.
- **Generic Developer Screen (`AboutDeveloperScreen`):** Suitable for *any* developer, team, or organization. Supports avatar, biography, website, direct contact (email, phone), code repositories (GitHub, GitLab), social profiles, and company apps list.
- **Ready-to-Use ConnectTag Screen (`ConnectTagInfoScreen`):** Pre-configured out-of-the-box with ConnectTag branding, social links, repositories, and apps.
- **Rich Social Networks Catalog:** 15+ built-in social networks and app stores (WhatsApp, Telegram, Facebook, X, Instagram, YouTube, LinkedIn, GitHub, GitLab, Google Play, etc.) with custom vector drawables and deep-link resolution.
- **Full Localization:** Arabic (`values-ar`) and English (`values`) resources included.
- **Material 3 & Edge-to-Edge:** Designed following modern Android & Material 3 guidelines.

---

## Installation

### 1. Add JitPack repository

In your root `settings.gradle.kts`:

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

In your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.connecttag:connecttaglibabout:1.0.1")
}
```

---

## Usage Guide

### 1. About Application (`org.connecttag.lib.kotlin.about.app`)

#### Using `AboutAppScreen`

```kotlin
import androidx.compose.runtime.Composable
import org.connecttag.lib.kotlin.about.app.AboutAppScreen
import org.connecttag.lib.kotlin.about.app.AboutAppWebsite
import org.connecttag.lib.kotlin.about.app.AboutAppRowAction
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline

@Composable
fun AppAboutRoute(
    onBack: () -> Unit,
    onUrlClick: (String) -> Unit,
    onOpenDeveloper: () -> Unit
) {
    AboutAppScreen(
        appName = "My App Name",
        versionText = "v1.2.0 (120)",
        description = "A powerful and modern Android application.",
        appIcon = R.mipmap.ic_launcher,
        packageName = "com.example.myapp",
        onBack = onBack,
        onUrlClick = onUrlClick,
        websites = listOf(
            AboutAppWebsite(label = "Official Website", url = "https://example.com")
        ),
        actions = listOf(
            AboutAppRowAction(title = "Help & FAQ", icon = Icons.Default.HelpOutline, url = "https://example.com/faq")
        ),
        onOpenDeveloperInfo = onOpenDeveloper
    )
}
```

#### Using `AboutAppPresentation` (Dialog / BottomSheet / FullScreen)

```kotlin
import org.connecttag.lib.kotlin.about.app.AboutAppDialog
import org.connecttag.lib.kotlin.about.app.AboutAppInfo
import org.connecttag.lib.kotlin.about.app.AboutAppDialogLabels

@Composable
fun ShowAboutDialog(
    appInfo: AboutAppInfo,
    onDismiss: () -> Unit,
    onUrlClick: (String) -> Unit
) {
    AboutAppDialog(
        appInfo = appInfo,
        title = "About",
        labels = AboutAppDialogLabels(
            infoContentDescription = "Info",
            closeContentDescription = "Close",
            websiteTitle = "Website"
        ),
        onDismissRequest = onDismiss,
        onWebsiteClick = onUrlClick,
        onSocialLinkClick = { link -> onUrlClick(link.network.webUrlPrefix + link.value) }
    )
}
```

---

### 2. About Developer (`org.connecttag.lib.kotlin.about.developer`)

#### A. Pre-configured ConnectTag Developer Screen

```kotlin
import androidx.compose.runtime.Composable
import org.connecttag.lib.kotlin.about.developer.ConnectTagInfoScreen

@Composable
fun ConnectTagDeveloperRoute(
    onBack: () -> Unit,
    onUrlClick: (String) -> Unit
) {
    ConnectTagInfoScreen(
        onBack = onBack,
        onUrlClick = onUrlClick
    )
}
```

#### B. Custom Developer Screen (`AboutDeveloperScreen`)

You can create a screen for any developer or organization:

```kotlin
import androidx.compose.runtime.Composable
import org.connecttag.lib.kotlin.about.developer.AboutDeveloperScreen
import org.connecttag.lib.kotlin.about.developer.DeveloperProfile
import org.connecttag.lib.kotlin.about.developer.DeveloperRepository
import org.connecttag.lib.kotlin.about.developer.DeveloperSocialLink
import org.connecttag.lib.kotlin.about.developer.DeveloperAppItem

@Composable
fun CustomDeveloperRoute(
    onBack: () -> Unit,
    onUrlClick: (String) -> Unit
) {
    val developerProfile = DeveloperProfile(
        name = "Acme Studios",
        description = "Building impactful mobile experiences.",
        logoRes = R.drawable.my_studio_logo,
        website = "https://acme.example.com",
        email = "contact@acme.example.com",
        repositories = listOf(
            DeveloperRepository("GitHub", "https://github.com/acme", iconKey = "github")
        ),
        socialLinks = listOf(
            DeveloperSocialLink("X", "https://x.com/acme", iconKey = "x"),
            DeveloperSocialLink("LinkedIn", "https://linkedin.com/company/acme", iconKey = "linkedin")
        ),
        apps = listOf(
            DeveloperAppItem("Google Play Portfolio", "https://play.google.com/store/apps/dev?id=123", iconKey = "googleplay")
        )
    )

    AboutDeveloperScreen(
        profile = developerProfile,
        onBack = onBack,
        onUrlClick = onUrlClick
    )
}
```

---

### 3. Backwards Compatibility

If your existing codebase was using `org.connecttag.lib.kotlin.about.BaseAboutAppScreen`, `org.connecttag.lib.kotlin.about.ConnectTagInfoScreen`, or `org.connecttag.lib.kotlin.core.aboutapp.*`:

- All types and screens are aliased and forward seamlessly to the new modular architecture.
- Existing code continues to compile without breaking changes.

---

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
