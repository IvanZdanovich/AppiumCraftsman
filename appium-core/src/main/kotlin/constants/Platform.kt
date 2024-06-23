package constants

import java.util.Locale

enum class Platform {
    ANDROID, IOS;

    companion object {
        fun fromString(platform: String): Platform {
            return when (platform.lowercase(Locale.getDefault())) {
                "android" -> ANDROID
                "ios" -> IOS
                else -> throw IllegalArgumentException("Unsupported platform: $platform")
            }
        }
    }
}
