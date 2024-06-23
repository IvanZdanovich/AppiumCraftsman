package pom

import ElementLocator
import io.appium.java_client.AppiumDriver

class LoginPage(
    driverProvider: () -> AppiumDriver,
    private val locator: ElementLocator = ElementLocator(driverProvider),
) {

    // Locators specific to the login page
    private val usernameField = "#username"
    private val passwordField = "#password"
    private val loginButton = "#loginButton"
    private val errorMessage = "#errorMessage"

    // Nested component
    val header: HeaderComponent = HeaderComponent(driverProvider, locator)

    // Actions
    fun enterUsername(username: String) {
        locator.enterText(usernameField, username)
    }

    fun enterPassword(password: String) {
        locator.enterText(passwordField, password)
    }

    fun clickLoginButton() {
        locator.clickElement(loginButton)
    }
}
