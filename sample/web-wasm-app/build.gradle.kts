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
    @Suppress("OPT_IN_USAGE")
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "sample.js"
            }
        }

        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":sample:shared"))
            }
        }
    }
}
