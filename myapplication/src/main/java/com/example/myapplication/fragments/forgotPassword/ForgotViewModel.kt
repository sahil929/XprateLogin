package com.example.myapplication.fragments.forgotPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.fragments.login.LoginViewModel
import com.example.myapplication.network.modals.*
import com.example.myapplication.repository.NetworkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForgotViewModel : LoginViewModel() {
    var repository: NetworkRepository = NetworkRepository()
    val _forgotPasswordResponse = MutableLiveData<ForgotPasswordResponse>()
    val forgotPasswordResponse: LiveData<ForgotPasswordResponse> = _forgotPasswordResponse
    private val _getResetLink = MutableLiveData<Boolean>()
    val getResetLink: LiveData<Boolean> = _getResetLink

    fun forgotPassword() {
        if (validateEmailOnly()) {
            showProgress()
            val forgotPassword = ForgotPassword(email = email.value!!)
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = repository.forgotPassword(forgotPassword)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _forgotPasswordResponse.value = (response.body())
                        loading.value = false
                    } else {
                        onError("Error : ${response.message()} ")
                    }
                }
            }
        }
    }
    fun getResetLink(){
        _getResetLink.value = true
    }
}