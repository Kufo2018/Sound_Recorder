package com.kuforiji.lei.utils

import android.app.Activity
import android.content.Context


const val DOWNLOAD_URI = "download_uri"

class SharedPrefs {

    fun saveDownloadUri(activity: Activity, uri: String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(DOWNLOAD_URI, uri)
            apply()
        }
    }

    fun getDownloadUri(activity: Activity): String? {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(DOWNLOAD_URI, "")
    }
}