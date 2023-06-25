// Copyright 2023, Christopher Banes and the project contributors
// SPDX-License-Identifier: Apache-2.0

plugins {
    id("com.android.library") version "8.0.2" apply false
    id("com.android.application") version "8.0.2" apply false

    kotlin("multiplatform") version "1.8.20" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("org.jetbrains.compose") version "1.4.0" apply false

    id("com.diffplug.spotless") version "6.19.0"
    id("com.vanniktech.maven.publish") version "0.25.2"
}

subprojects {
    apply(plugin = "com.diffplug.spotless")

    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")
            ktlint()

            licenseHeaderFile(rootProject.file("spotless/cb-copyright.txt"))
                .named("cb-existing")
                .onlyIfContentMatches("Copyright \\d+,* Christopher Banes")
            licenseHeaderFile(rootProject.file("spotless/cb-copyright.txt"))
                .named("cb-none")
                .onlyIfContentMatches("^(?!// Copyright).*\$")
        }

        kotlinGradle {
            target("**/*.kts")
            targetExclude("$buildDir/**/*.kts")
            ktlint()

            licenseHeaderFile(rootProject.file("spotless/cb-copyright.txt"), "(^(?![\\/ ]\\**).*$)")
                .named("cb-existing")
                .onlyIfContentMatches("Copyright \\d+,* Christopher Banes")
            licenseHeaderFile(rootProject.file("spotless/cb-copyright.txt"), "(^(?![\\/ ]\\**).*$)")
                .named("cb-none")
                .onlyIfContentMatches("^(?!// Copyright).*\$")
        }
    }
}