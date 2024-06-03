// android/app/src/main/java/com/mynewproject/IconChangerModule.kt
package com.mynewproject

import android.app.Activity
import android.content.ComponentName
import android.content.pm.PackageManager
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class IconChangerModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "IconChanger"
    }

    @ReactMethod
    fun changeIcon(iconName: String?, promise: Promise) {
        try {
            val activity: Activity? = currentActivity
            if (activity == null) {
                promise.reject("error", "Activity is null")
                return
            }

            val pm: PackageManager = activity.packageManager
            val normal = ComponentName(activity, "com.anonymous.MainActivity")
            val newIcon = ComponentName(activity, "com.anonymous.MainActivityIcon${iconName}")

            pm.setComponentEnabledSetting(normal, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)
            pm.setComponentEnabledSetting(newIcon, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)

            promise.resolve(null)
        } catch (e: Exception) {
            promise.reject("error", e.message)
        }
    }
}
