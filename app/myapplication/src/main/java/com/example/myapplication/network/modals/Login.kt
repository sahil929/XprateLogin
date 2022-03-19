package com.example.myapplication.network.modals

data class Login(
    val connectedPartyUserId: String,
    val email: String,
    val password: String
)