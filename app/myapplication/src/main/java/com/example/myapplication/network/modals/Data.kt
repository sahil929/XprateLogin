package com.example.myapplication.network.modals

data class Data(
    val allowedOrigins: List<String>,
    val name: String,
    val picMedia: PicMedia,
    val userId: String,
    val userAccessToken:String=""
)