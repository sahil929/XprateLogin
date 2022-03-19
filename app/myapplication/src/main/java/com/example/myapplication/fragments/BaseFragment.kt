package com.example.myapplication.fragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication.BaseActivity
import com.example.myapplication.R

open class BaseFragment : Fragment() {

    fun showSignUpScreen(view: View) {
        Navigation.findNavController(view).navigate(R.id.registerScreen)
    }

    fun showLoginScreen(view: View) {
        Navigation.findNavController(view).navigate(R.id.loginScreen)
    }
    fun showForgotPasswordScreen(view: View) {
        Navigation.findNavController(view).navigate(R.id.forgotPasswordScreen)
    }
    fun showForgotPasswordSucessScreen(view: View) {
        Navigation.findNavController(view).navigate(R.id.forgotPasswordSuccessScreen)
    }
    fun showRegisterSucessScreen(view: View) {
        Navigation.findNavController(view).navigate(R.id.registerSuccessScreen)
    }
    fun showProgress(){
        (requireActivity() as BaseActivity).showProgress()
    }
    fun hideProgress(){
        //(requireActivity() as BaseActivity).hideProgress()
    }
    fun showToastMessage(message:String){
        Toast.makeText(requireActivity(),message,Toast.LENGTH_SHORT).show()
    }
    fun showWebActivity(url:String){
        (requireActivity() as BaseActivity).startWebActivity(url)
    }
}