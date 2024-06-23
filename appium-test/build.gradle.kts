plugins {
    kotlin("jvm")
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin")
    }
    test {
        java.srcDirs("src/test/kotlin")
    }
}

tasks.register<Test>("runAppiumTests") {
    description = "Runs Appium tests with specified parameters"
    group = JavaBasePlugin.VERIFICATION_GROUP

    useTestNG {
        if (project.hasProperty("suite")) {
            val suiteToRun = project.findProperty("suite")
            suites("resources/$suiteToRun")
        } else {
            val platform = project.findProperty("platform") ?: "android"
            systemProperty("platform", platform)
            systemProperty("hubUrl", project.findProperty("hubUrl") ?: "")
            systemProperty("appPath", project.findProperty("appPath") ?: "")
            systemProperty("deviceName", project.findProperty("deviceName") ?: "")
            systemProperty("platformVersion", project.findProperty("platformVersion") ?: "")
            systemProperty("udid", project.findProperty("udid") ?: "")
        }
    }
}
