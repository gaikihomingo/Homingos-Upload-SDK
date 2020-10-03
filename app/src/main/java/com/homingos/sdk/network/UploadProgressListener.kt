package com.homingos.sdk.network

internal interface UploadProgressListener {

    fun onProgressUpdate(percentage: Int)

}