[![Maven Central](https://img.shields.io/maven-central/v/dev.chrisbanes.material3/material3-window-size-class-multiplatform)](https://search.maven.org/search?q=g:dev.chrisbanes.material3) ![Build status](https://github.com/chrisbanes/material3-windowsizeclass-multiplatform/actions/workflows/build.yml/badge.svg)

## Compose Multiplatform: Material 3 Window Size Class

The [Material 3 Window size classes](https://m3.material.io/foundations/layout/applying-layout/window-size-classes) are a set of  opinionated breakpoints, the window size at which a layout needs to change to match available space, device conventions, and ergonomics. All devices fall into one of three Material Design window size classes: compact, medium, or expanded. Rather than designing for an ever increasing number of display states, focusing on window class sizes ensures layouts work across a range of devices.

The `androidx.compose.material3:material3-window-size-class` library is available for Jetpack Compose, but it is not currently available for Compose Multiplatform. This library changes that, by providing the [WindowSizeClass](https://developer.android.com/reference/kotlin/androidx/compose/material3/windowsizeclass/WindowSizeClass) for many of the platforms supported by Compose Multiplatform.

| Platform      | Supported        | Sample                                                                                                            |
|---------------|------------------|-------------------------------------------------------------------------------------------------------------------|
| Android       | ✅                | [android-app](https://github.com/chrisbanes/material3-windowsizeclass-multiplatform/tree/main/sample/android-app) |
| iOS           | ✅                | [ios-app](https://github.com/chrisbanes/material3-windowsizeclass-multiplatform/tree/main/sample/ios-app)         |
| Desktop (JVM) | ✅                | [desktop-app](https://github.com/chrisbanes/material3-windowsizeclass-multiplatform/tree/main/sample/desktop-app) |
| Web           | ✅ (experimental) | [web-app](https://github.com/chrisbanes/material3-windowsizeclass-multiplatform/tree/main/sample/web-app)         |

## Usage

Usage is very simple:

```kotlin
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass

@Composable
fun MyApplication() {
    val windowSizeClass = calculateWindowSizeClass()

    // TODO: use windowSizeClass
}
```

You'll note that I have kept the package name the same as that in AndroidX. This is to enable easy migration for when support is added to Compose Multiplatform ([tracking issue](https://github.com/JetBrains/compose-multiplatform/issues/2404)).

## Download

[![Maven Central](https://img.shields.io/maven-central/v/dev.chrisbanes.material3/material3-window-size-class-multiplatform)](https://central.sonatype.com/namespace/dev.chrisbanes.material3)

```kotlin
val commonMain by getting {
    dependencies {
        implementation("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.5.0")
    }
}
```

## License

```
Copyright 2022 The Android Open Source Project
Portions 2023 Christopher Banes
 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
