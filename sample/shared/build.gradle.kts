// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0


import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    applyDefaultHierarchyTemplate()

    jvm()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    js(IR) {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":lib"))
                api(compose.material3)
            }
        }
    }
}

android {
    namespace = "dev.chrisbanes.material3.windowsizeclass.sample.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}
