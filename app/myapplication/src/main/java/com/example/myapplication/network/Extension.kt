package com.example.myapplication.network

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.BaseActivity
import com.example.myapplication.network.modals.XprateApp
import com.example.myapplication.storage.Prefrences
import retrofit2.Retrofit

fun Retrofit.Builder.appBaseUrl(url: String): Retrofit.Builder {
    return this.baseUrl(url)
}

fun Retrofit.Builder.convertFactory(): Retrofit.Builder {
    return this.addConverterFactory(gsonConverterFactory.value)
}

fun Retrofit.Builder.buildRetrofit(): Retrofit {
    return this.build()
}

fun <T> Retrofit.createInstance(service: Class<T>): T {
    return this.create(service)
}

fun XprateApp.show(activity: AppCompatActivity, result: ActivityResultLauncher<Intent>) {
    val intent = Intent(activity, BaseActivity::class.java)
        .putExtra(Prefrences.SECRET_KEY, this.org_secret_key)
        .putExtra(Prefrences.ACCESS_TOKEN,this.org_secret_token)
    result.launch(intent)
}
