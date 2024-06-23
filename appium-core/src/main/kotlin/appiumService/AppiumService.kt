package appiumService

import constants.Platform
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import java.net.URI
import org.openqa.selenium.remote.DesiredCapabilities

class AppiumService {
    private val drivers: MutableMap<String, ThreadLocal<AppiumDriver>> = mutableMapOf()

    val currentPlatform: Platform
        get() = drivers[Thread.currentThread().name]?.get()?.let { driver ->
            when (driver) {
                is IOSDriver -> Platform.IOS
                is AndroidDriver -> Platform.ANDROID
                else -> error("Unknown driver type: ${driver::class.simpleName}")
            }
        } ?: throw IllegalStateException("Driver for thread ${Thread.currentThread().name} has not been initialized. Please start the driver before attempting to get it.")

    fun startDriver(hubURL: String, capabilities: DesiredCapabilities, threadName: String) {
        val platform = Platform.fromString(capabilities.getCapability("platformName").toString())
        validateCapabilities(platform, capabilities)

        val driverThreadLocal = ThreadLocal<AppiumDriver>()
        val driver: AppiumDriver = when (platform) {
            Platform.IOS -> IOSDriver(URI.create(hubURL).toURL(), capabilities)
            Platform.ANDROID -> AndroidDriver(URI.create(hubURL).toURL(), capabilities)
        }
        driverThreadLocal.set(driver)
        drivers[threadName] = driverThreadLocal
    }

    private fun validateCapabilities(platform: Platform, capabilities: DesiredCapabilities) {
        val requiredCapabilities = when (platform) {
            Platform.ANDROID -> listOf("platformName", "app", "appPackage", "appActivity", "deviceName", "platformVersion", "udid")
            Platform.IOS -> listOf("platformName", "app", "bundleId", "deviceName", "platformVersion", "udid")
        }

        val missingCapabilities = requiredCapabilities.filter { capabilities.getCapability(it) == null }
        require(missingCapabilities.isEmpty()) {
            "Missing required capabilities: ${missingCapabilities.joinToString(", ")} for platform: $platform"
        }
    }

    fun getDriver(threadName: String): AppiumDriver = drivers[threadName]?.get()
        ?: throw IllegalStateException("Driver for thread $threadName has not been initialized. Please start the driver before attempting to get it.")

    fun quitDriver(threadName: String) {
        drivers[threadName]?.get()?.quit()
        drivers.remove(threadName)
    }

    fun stopAllDrivers() {
        drivers.forEach { (_, driver) -> driver.get()?.quit() }
        drivers.clear()
    }
}
