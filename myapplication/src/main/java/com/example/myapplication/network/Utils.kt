package com.example.myapplication.network

import com.example.myapplication.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor




private fun getRetrofitInstance(): ApiServices {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .appBaseUrl(baseUrl.value)
            .client(client)
            .convertFactory()
            .buildRetrofit()
       return retrofit.createInstance(ApiServices::class.java)
}
val retrofit: ApiServices by lazy { getRetrofitInstance() }
val baseUrl = lazy {
    BuildConfig.BASE_URL
}
val gsonConverterFactory = lazy {
    GsonConverterFactory.create()
}