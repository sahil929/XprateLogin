package com.example.myapplication.repository

import com.example.myapplication.network.modals.*
import com.example.myapplication.network.retrofit
import retrofit2.Response

class NetworkRepository {

    suspend fun getAccessToken(headers: Map<String, String?>) :Response<Token> =
        retrofit.getAccessToken(headers)
    suspend fun login(headers: Map<String, String?>, login:Login) :Response<Token> =
        retrofit.login(headers,true,login)
    suspend fun register(headers: Map<String, String?>, register: Register) :Response<RegisterResponse> =
        retrofit.register(headers,false,register)
    suspend fun forgotPassword(forgotPassword: ForgotPassword) :Response<ForgotPasswordResponse> =
        retrofit.forgotPassword(forgotPassword)

    companion object{
        const val SECRET_KEY= "org-secret-key"
        const val SECRET_TOKEN= "org-access-token"
        const val CONNECTED_PARTY_USERID= "connectedPartyUserId"
    }

}