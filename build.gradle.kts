plugins {
    kotlin("jvm") version "2.0.0"
}

val appiumCoreProject: String by project
val appiumTestProject: String by project

val javaVersion = "21"
// Appium - Selenium compatibility matrix: https://github.com/appium/java-client?tab=readme-ov-file#compatibility-matrix
val appiumVersion = "9.2.3"
val testNGVersion = "7.10.2"
val seleniumVersion = "4.19.0"
val jacksonModuleVersion = "2.16.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion))
        }
    }
}

project(":$appiumCoreProject") {
    dependencies {
        implementation("io.appium:java-client:${appiumVersion}")
        implementation("org.seleniumhq.selenium:selenium-java:${seleniumVersion}")
    }
}

project(":$appiumTestProject") {
    dependencies {
        implementation(project(":$appiumCoreProject"))
        implementation("io.appium:java-client:${appiumVersion}")
        implementation("org.seleniumhq.selenium:selenium-java:${seleniumVersion}")
        implementation("org.testng:testng:${testNGVersion}")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${jacksonModuleVersion}")
    }
}

