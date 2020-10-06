package com.homingos.sdk.utils

import android.content.Context
import com.homingos.sdk.utils.Constants.BASE_REDIRECTION_URL
import com.homingos.sdk.utils.Constants.BASE_REDIRECTION_URL_DEBUG
import com.homingos.sdk.utils.Constants.BUCKET_NAME
import com.homingos.sdk.utils.Constants.BUCKET_NAME_DEBUG
import com.homingos.sdk.utils.Constants.PREFIX
import com.homingos.sdk.utils.Constants.PREFIX_DEBUG
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

fun getBucketName(context: Context): String {
    return if (context.isDebugBuild()) {
        BUCKET_NAME_DEBUG
    } else {
        BUCKET_NAME
    }
}

fun getPrefix(context: Context): String {
    return if (context.isDebugBuild()) {
        PREFIX_DEBUG
    } else {
        PREFIX
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