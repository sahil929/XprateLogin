package com.example.myapplication.network.modals

data class Register(
    val connectedPartyUserId: String,
    val email: String,
    val password: String,
    val name:String
)