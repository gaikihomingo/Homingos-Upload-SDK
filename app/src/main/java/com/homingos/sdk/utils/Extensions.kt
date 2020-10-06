package com.homingos.sdk.utils

import android.content.Context
import com.homingos.sdk.utils.Constants.BASE_REDIRECTION_URL
import com.homingos.sdk.utils.Constants.BASE_REDIRECTION_URL_DEBUG
import com.homingos.sdk.utils.Constants.UPLOAD_URL
import com.homingos.sdk.utils.Constants.UPLOAD_URL_DEBUG
import java.lang.reflect.Field

fun getUploadUrl(context: Context): String {
    return if (context.isDebugBuild()) {
        UPLOAD_URL_DEBUG
    } else {
        UPLOAD_URL
    }
}

fun getRedirectionUrl(context: Context): String {
    return if (context.isDebugBuild()) {
        BASE_REDIRECTION_URL_DEBUG
    } else {
        BASE_REDIRECTION_URL
    }
}

private fun Context.isDebugBuild(): Boolean {
    try {
        val clazz = Class.forName("$packageName.BuildConfig")
        val field: Field = clazz.getField("DEBUG")
        return field.get(null) as Boolean
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
    return false
}