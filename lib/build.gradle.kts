// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0


import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.vanniktech.maven.publish")
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    jvm()
    android {
        publishLibraryVariants("release")

        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "lib"
            isStatic = true
        }
    }

    js(IR) {
        browser()
    }

    configure(targets) {
        if (this is KotlinNativeTarget && konanTarget.family.isAppleFamily) {
            compilations.getByName("main") {
                val objc by cinterops.creating {
                    defFile(project.file("src/iosMain/def/objc.def"))
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.ui)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.window:window:1.1.0")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.willowtreeapps.assertk:assertk:0.27.0")
            }
        }
    }
}

android {
    namespace = "androidx.compose.material3.windowsizeclass"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
    }
}
