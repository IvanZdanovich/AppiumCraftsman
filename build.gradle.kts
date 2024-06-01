plugins {
    kotlin("jvm") version "2.0.0"
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
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

    dependencies {
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }
}