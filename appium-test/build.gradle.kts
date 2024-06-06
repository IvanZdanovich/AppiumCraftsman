plugins {
    kotlin("jvm")
}

val testNGVersion = "7.10.2"

dependencies {
    testImplementation("org.testng:testng:${testNGVersion}")
}
