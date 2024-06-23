package pom

import ElementLocator
import io.appium.java_client.AppiumDriver

class MenuComponent(
    driverProvider: () -> AppiumDriver,
    private val locator: ElementLocator
) {

    private val settingsButton = "#settingsButton"
    private val logoutButton = "#logoutButton"

    fun clickSettingsButton() {
        locator.clickElement(settingsButton)
    }

    fun clickLogoutButton() {
        locator.clickElement(logoutButton)
    }
}