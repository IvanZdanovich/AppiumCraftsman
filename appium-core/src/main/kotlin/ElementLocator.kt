import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.time.Duration

class ElementLocator(private val driverProvider: () -> AppiumDriver) {

    init {
        driverProvider().manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
    }

    fun toBy(locator: String): By {
        return when {
            locator.startsWith("//") -> By.xpath(locator)
            locator.startsWith("#") -> By.id(locator.substring(1))
            locator.startsWith(".") -> By.className(locator.substring(1))
            locator.startsWith("css=") -> By.cssSelector(locator.substring(4))
            locator.startsWith("name=") -> By.name(locator.substring(5))
            locator.startsWith("text=") -> By.xpath("//*[text()='${locator.substring(5)}']")
            else -> throw IllegalArgumentException("Unknown locator type: $locator")
        }
    }

    fun findElement(locator: String): WebElement {
        return driverProvider().findElement(toBy(locator))
    }

    fun clickElement(locator: String) {
        findElement(locator).click()
    }

    fun enterText(locator: String, text: String) {
        findElement(locator).apply {
            clear()
            sendKeys(text)
        }
    }

    fun getText(locator: String): String {
        return findElement(locator).text
    }
}