import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    kotlin("js") version "1.4.10"
}

group = "com.github.kanishi.day.background.generator"
version = "0.1.0"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://kotlin.bintray.com/kotlinx")
    mavenCentral()
}

kotlin {
    js {
        browser {
            webpackTask {
                sourceMaps = false
            }
            binaries.executable()
        }
    }

    sourceSets["main"].dependencies {
        implementation(kotlin("stdlib-js", KotlinCompilerVersion.VERSION))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.9")
        implementation(npm("html-webpack-plugin", "^4.5.0"))
    }
}
