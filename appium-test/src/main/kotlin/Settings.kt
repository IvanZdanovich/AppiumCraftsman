import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

data class Settings(
    val hubUrl: String,
    val capabilities: Capabilities,
){
    companion object Loader{
        fun loadParameters(filePath: String): Settings {
            val json = File(filePath).readText()
            return jacksonObjectMapper().readValue(json)
        }
    }
}

data class Capabilities(
    var platformName: String,
    var automationName: String,
    var app: String,
    var noReset: Boolean = false,
    val fullReset: Boolean = false,
    val newCommandTimeout: Int = 240,
    val autoAcceptAlerts: Boolean = true,
    val eventTimings: Boolean = false,
    val orientation: String? = null,
    val language: String? = null,
    val locale: String? = null,
    val autoWebview: Boolean = false,
    val printPageSourceOnFindFailure: Boolean = false,
    // iOS Specific
    val wdaLaunchTimeout: Int? = null,
    val wdaConnectionTimeout: Int? = null,
    val useNewWDA: Boolean? = null,
    val startIWDP: Boolean? = null,
    val waitForQuiescence: Boolean? = null,
    val bundleId: String? = null,
    val usePrebuiltWDA: Boolean? = null,
    val shouldUseSingletonTestManager: Boolean? = null,
    val iosInstallPause: Int? = null,
    val autoDismissAlerts: Boolean? = null,
    val useXctestrunFile: Boolean? = null,
    val xcodeOrgId: String? = null,
    val xcodeSigningId: String? = null,
    val updatedWDABundleId: String? = null,
    val waitForIdleTimeout: Int? = null,
    val useNativeCachingStrategy: Boolean? = null,
    var platformVersion: String,
    var deviceName: String,
    var udid: String,
    val wdaLocalPort: Int? = null,
    // Android Specific
    val appPackage: String? = null,
    val appActivity: String? = null,
    val autoGrantPermissions: Boolean? = null,
    val skipDeviceInitialization: Boolean? = null,
    val skipServerInstallation: Boolean? = null,
    val ignoreHiddenApiPolicyError: Boolean? = null,
    val disableWindowAnimation: Boolean? = null,
    val unicodeKeyboard: Boolean? = null,
    val resetKeyboard: Boolean? = null,
    val chromeOptions: Map<String, Any>? = null,
    val allowTestPackages: Boolean? = null,
    val autoLaunch: Boolean? = null,
    val adbPort: Int? = null,
    val dontStopAppOnReset: Boolean? = null,
    val systemPort: Int? = null,
    val deviceReadyTimeout: Int? = null,
    val adbExecTimeout: Int? = null,
    val skipUnlock: Boolean? = null,
)



