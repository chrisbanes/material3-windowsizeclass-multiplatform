// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0


plugins {
    kotlin("android")
    id("com.android.application")
    id("org.jetbrains.compose")
}

android {
    namespace = "dev.chrisbanes.material3.windowsizeclass.sample.android"
    compileSdk = 33

    defaultConfig {
        applicationId = "dev.chrisbanes.material3.windowsizeclass.sample.android"
        minSdk = 24
        targetSdk = 33

        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":sample:shared"))
    implementation("androidx.activity:activity-compose:1.7.2")
}
