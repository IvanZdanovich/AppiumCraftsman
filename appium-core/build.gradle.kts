plugins {
    kotlin("jvm")
}

// Appium - Selenium compatibility matrix: https://github.com/appium/java-client?tab=readme-ov-file#compatibility-matrix
val appiumVersion = "9.2.3"
val seleniumVersion = "4.19.0"

dependencies {
    implementation("io.appium:java-client:${appiumVersion}")
    implementation("org.seleniumhq.selenium:selenium-java:${seleniumVersion}")
}
