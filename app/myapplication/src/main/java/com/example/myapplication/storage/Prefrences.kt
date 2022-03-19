package com.example.myapplication.storage

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

object Prefrences {

    const val ACCESS_TOKEN = "access_token"
    const val SECRET_KEY = "secret_key"
    const val CONNECTED_USER_ID = "connected_user_id"
    const val IMAGE_URL = "image_url"
    const val API_TOKEN = "api_token"

    fun storeToken(activity: Activity, accessToken: String, accessKey: String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(ACCESS_TOKEN, accessToken)
            putString(SECRET_KEY, accessKey)
            apply()
        }
    }

    fun storeUserId(activity: Activity, userId: String,image_url:String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(CONNECTED_USER_ID, userId)
            putString(IMAGE_URL,image_url)
            apply()
        }
    }

    fun storeApiToken(activity: Activity, apiToken: String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(API_TOKEN, apiToken)
            apply()
        }
    }

    fun getToken(activity: Activity): String? {
        return activity.getPreferences(Context.MODE_PRIVATE).getString(ACCESS_TOKEN, "")
    }

    fun getUserId(activity: Activity): String? {
        return activity.getPreferences(Context.MODE_PRIVATE).getString(CONNECTED_USER_ID, "")
    }
    fun getImageUrl(activity: Activity): String? {
        return activity.getPreferences(Context.MODE_PRIVATE).getString(IMAGE_URL, "")
    }

    fun getSecretKey(activity: Activity): String? {
        return activity.getPreferences(Context.MODE_PRIVATE).getString(SECRET_KEY, "")
    }
    fun getApiToken(activity: Activity): String? {
        return activity.getPreferences(Context.MODE_PRIVATE).getString(API_TOKEN, "")
    }
}