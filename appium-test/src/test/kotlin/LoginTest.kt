import io.appium.java_client.AppiumDriver
import org.testng.annotations.Test
import pom.LoginPage

class LoginTest : BaseTest() {

    private val driverProvider: () -> AppiumDriver = { driver.get() }
    private val locator = ElementLocator(driverProvider)

    @Test
    fun testSuccessfulLogin() {
        val loginPage = LoginPage(driverProvider, locator)

        loginPage.enterUsername("validUsername")
        loginPage.enterPassword("validPassword")
        loginPage.clickLoginButton()

        loginPage.header.clickHomeButton()
        loginPage.header.menu.clickSettingsButton()
    }

    @Test
    fun testFailedLogin() {
        val loginPage = LoginPage(driverProvider, locator)

        loginPage.enterUsername("invalidUsername")
        loginPage.enterPassword("invalidPassword")
        loginPage.clickLoginButton()

    }
}