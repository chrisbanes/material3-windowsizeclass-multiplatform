pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "material3-windowsizeclass-multiplatform"

include(":lib")
include(":sample:shared")
include(":sample:android-app")
include(":sample:desktop-app")
include(":sample:web-js-app")
include(":sample:web-wasm-app")
