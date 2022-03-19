package com.example.myapplication.network

import com.example.myapplication.network.modals.*
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    @GET("api/integrations/organisation/details")
    suspend fun getAccessToken(
        @HeaderMap headers: Map<String, String?>,
        @Query("fromIntegrationModule")
        fromIntegrationModule: Boolean = false
    ): Response<Token>

    @POST("api/users/registerUser")
    suspend fun login(
        @HeaderMap headers: Map<String, String?>,
        @Query("isSignIn") isSignIn:Boolean = true,
        @Body login: Login
    ): Response<Token>

    @POST("api/users/registerUser")
    suspend fun register(
        @HeaderMap headers: Map<String, String?>,
        @Query("isSignIn") isSignIn:Boolean = false,
        @Body login: Register
    ): Response<RegisterResponse>

    @PUT("api/users/password/forgot")
    suspend fun forgotPassword(
        @Body forgotPassword: ForgotPassword
    ): Response<ForgotPasswordResponse>
}