// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

compose {
    experimental {
        web.application {}
    }
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":sample:shared"))
            }
        }
    }
}
