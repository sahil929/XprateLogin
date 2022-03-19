package com.example.myapplication.fragments.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.fragments.login.LoginViewModel
import com.example.myapplication.network.modals.Register
import com.example.myapplication.network.modals.RegisterResponse
import com.example.myapplication.repository.NetworkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel : LoginViewModel() {
    private var repository: NetworkRepository = NetworkRepository()
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse
    fun register() {
        if (validateRegisterForm()) {
            showProgress()
            val register = Register(
                connectedPartyUserId = connectedUserId,
                email = email.value!!,
                password = password.value!!,
                name = fullName.value!!)
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = repository.register(headers, register)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _registerResponse.value = (response.body())
                        loading.value = false
                    } else {
                        onError("Error : ${response.message()} ")
                    }
                }
            }
        }
    }
}