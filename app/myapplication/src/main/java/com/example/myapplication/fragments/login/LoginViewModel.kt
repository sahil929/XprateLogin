package com.example.myapplication.fragments.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.network.modals.Login
import com.example.myapplication.network.modals.Token
import com.example.myapplication.repository.NetworkRepository
import com.example.myapplication.utils.LiveDataValidator
import com.example.myapplication.utils.LiveDataValidatorResolver
import kotlinx.coroutines.*
import java.util.regex.Matcher
import java.util.regex.Pattern

open class LoginViewModel : ViewModel() {

    private val errorMessage = MutableLiveData<String>()

    private val _authenticateResponse = MutableLiveData<Token>()
    val authenticateResponse: LiveData<Token> = _authenticateResponse

    private val _showLoginScreen = MutableLiveData<Boolean>()
    val showLoginScreen: LiveData<Boolean> = _showLoginScreen

    private val _showSignUpScreen = MutableLiveData<Boolean>()
    val showSignUpScreen: LiveData<Boolean> = _showSignUpScreen

    private val _showForgotPasswordScreen = MutableLiveData<Boolean>()
    val showForgotPasswordScreen: LiveData<Boolean> = _showForgotPasswordScreen

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    private val _loginResponse = MutableLiveData<Token>()
    val loginResponse: LiveData<Token> = _loginResponse

    private val _showTermsOfServiceScreen = MutableLiveData<Boolean>()
    val showTermsOfServiceScreen: LiveData<Boolean> = _showTermsOfServiceScreen

    private val _showPrivacyPolicyScreen = MutableLiveData<Boolean>()
    val showPrivacyPolicyScreen: LiveData<Boolean> = _showPrivacyPolicyScreen

    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    private var loginRepository: NetworkRepository = NetworkRepository()
    var email: MutableLiveData<String> = MutableLiveData("")
    var password: MutableLiveData<String> = MutableLiveData("")
    var headers: MutableMap<String, String?> = mutableMapOf()
    var connectedUserId: String = ""
    var fullName: MutableLiveData<String> = MutableLiveData("")



    val nameValidator = LiveDataValidator(fullName).apply {
        //Whenever the condition of the predicate is true, the error message should be emitted
        addRule("Name  is required") { it.isNullOrBlank() }
    }
    val emailValidator = LiveDataValidator(email).apply {
        //Whenever the condition of the predicate is true, the error message should be emitted
        addRule("Email  is required") { it.isNullOrBlank() }
        addRule("Email  is invalid") {
            !Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches()
        }
    }
    val passwordValidator = LiveDataValidator(password).apply {
        //We can add multiple rules.
        //However the order in which they are added matters because the rules are checked one after the other
        addRule("Password is required") { it.isNullOrBlank() }
        addRule("Password must follow all conditions") {
            !isValidPassword(password.value.toString())
        }
    }
    val passwordEmptyValidator = LiveDataValidator(password).apply {
        //We can add multiple rules.
        //However the order in which they are added matters because the rules are checked one after the other
        addRule("Password is required") { it.isNullOrBlank() }
    }

    val isLoginFormValidMediator = MediatorLiveData<Boolean>()

    init {
        isLoginFormValidMediator.value = false
        isLoginFormValidMediator.addSource(email) { }
        //isLoginFormValidMediator.addSource(fullName) { validateForm() }
        isLoginFormValidMediator.addSource(password) { }
    }

    private fun validateForm(): Boolean {
        val validators = listOf(emailValidator, passwordEmptyValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        return validatorResolver.isValid()
    }
    fun validateRegisterForm():Boolean{
        val validators = listOf(emailValidator,nameValidator,passwordValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        return validatorResolver.isValid()
    }
    fun validateEmailOnly():Boolean{
        val validators = listOf(emailValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        return validatorResolver.isValid()
    }

    open fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun getAccessToken() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = loginRepository.getAccessToken(headers)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _authenticateResponse.value = (response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    fun login() {
        if (validateForm()) {
            showProgress()
            val login = Login(
                connectedPartyUserId = connectedUserId,
                email = email.value!!,
                password = password.value!!
            )
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = loginRepository.login(headers, login)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _loginResponse.value = (response.body())
                        loading.value = false
                    } else {
                        onError("Error : ${response.message()} ")
                    }
                }
            }

        }
    }

    fun showSignUpScreen() {
        _showSignUpScreen.value = true
    }

    fun showForgotPasswordScreen() {
        _showForgotPasswordScreen.value = true
    }

    fun showLoginScreen() {
        _showLoginScreen.value = true
    }
    fun showTermsScreen() {
        _showTermsOfServiceScreen.value = true
    }
    fun showPrivacyScreen() {
        _showPrivacyPolicyScreen.value = true
    }

    fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
    fun showProgress(){
        _showProgress.value = true
    }

}