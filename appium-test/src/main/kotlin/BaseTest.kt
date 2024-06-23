import appiumService.AppiumService
import constants.Platform
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Parameters

open class BaseTest {
    private val appiumService = AppiumService()
    protected lateinit var driver: ThreadLocal<AppiumDriver>

    @Parameters(
        "settingsPath",
        "hubUrl",
        "platform",
        "appPath",
        "deviceName",
        "platformVersion",
        "udid",
    )

    @BeforeSuite
    fun setUp(
        settingsPath: String?,
        hubUrl: String?,
        platform: String?,
        platformVersion: String?,
        appPath: String?,
        deviceName: String?,
        udid: String?,
    ) {
        val (hubUrlParam, capabilities) = loadAndMergeCapabilities(
            settingsPath, hubUrl, platform, platformVersion, appPath, deviceName, udid
        )
        startDriverForConfig(hubUrlParam, capabilities, Thread.currentThread().name)
    }

    private fun loadAndMergeCapabilities(
        settingsPath: String?,
        hubUrl: String?,
        platform: String?,
        platformVersion: String?,
        appPath: String?,
        deviceName: String?,
        udid: String?,
    ): Pair<String, Capabilities> {
        val capabilitiesPath = settingsPath ?: defaultCapabilitiesPath(platform)
        val settings = Settings.loadParameters(capabilitiesPath)
        val hubUrlParam = hubUrl ?: settings.hubUrl

        val capabilities = settings.capabilities.apply {
            platform?.let { platformName = it }
            platformVersion?.let { this.platformVersion = it }
            appPath?.let { app = it }
            deviceName?.let { this.deviceName = it }
            udid?.let { this.udid = it }
        }
        return hubUrlParam to capabilities
    }

    private fun defaultCapabilitiesPath(platform: String?): String {
        requireNotNull(platform) { "Platform must be specified if settingsPath is not provided." }
        return if (Platform.fromString(platform) == Platform.IOS) {
            "resources/ios/ios_device1.json"
        } else {
            "resources/android/android_device1.json"
        }
    }

    private fun startDriverForConfig(hubUrl: String, capabilities: Capabilities, threadName: String) {
        try {
            val desiredCapabilities = DesiredCapabilities().apply {
                capabilities::class.members
                    .filter { it.parameters.isEmpty() }
                    .forEach { member ->
                        member.call(capabilities)?.let { value ->
                            setCapability(member.name, value)
                        }
                    }
            }
            appiumService.startDriver(hubUrl, desiredCapabilities, threadName)
            driver = ThreadLocal.withInitial { appiumService.getDriver(threadName) }
        } catch (e: Exception) {
            throw RuntimeException("Failed to set up driver for thread $threadName with error: ${e.message}", e)
        }
    }

    fun getDriver(): AppiumDriver {
        return driver.get()
            ?: throw IllegalStateException("Driver for thread ${Thread.currentThread().name} has not been initialized.")
    }

    @AfterSuite
    fun tearDown() {
        val threadName = Thread.currentThread().name
        appiumService.quitDriver(threadName)
    }
}
