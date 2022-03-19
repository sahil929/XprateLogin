package com.example.myapplication.network.modals

data class RegisterResponse(
    val data: Data,
    val isSuccess: Boolean,
    val error:String
)