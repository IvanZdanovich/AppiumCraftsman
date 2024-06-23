package pom

import ElementLocator
import io.appium.java_client.AppiumDriver

class HeaderComponent(
    driverProvider: () -> AppiumDriver,
    private val locator: ElementLocator,
) {

    private val homeButton = "#homeButton"
    private val menuButton = "#menuButton"

    // Nested component
    val menu: MenuComponent = MenuComponent(driverProvider, locator)

    // Actions
    fun clickHomeButton() {
        locator.clickElement(homeButton)
    }

    fun clickMenuButton() {
        locator.clickElement(menuButton)
    }
}
