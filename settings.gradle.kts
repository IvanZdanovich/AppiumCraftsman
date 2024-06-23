rootProject.name = "craftsman"

val appiumCoreProject: String by settings
val appiumTestProject: String by settings

include(appiumCoreProject, appiumTestProject)
