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
    }
}

rootProject.name = "material3-windowsizeclass-multiplatform"

include(":lib")
include(":sample:shared")
include(":sample:android-app")
include(":sample:desktop-app")
