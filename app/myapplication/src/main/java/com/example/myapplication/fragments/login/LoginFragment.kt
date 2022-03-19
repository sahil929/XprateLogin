package com.example.myapplication.fragments.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.TooltipCompat
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.fragments.BaseFragment
import com.example.myapplication.repository.NetworkRepository
import com.example.myapplication.storage.Prefrences
import com.example.myapplication.utils.Constants
import com.example.myapplication.utils.TooltipWindow
import com.google.gson.Gson


class LoginFragment : BaseFragment() {

    private var tipWindow: TooltipWindow?=null
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginViewModel=loginViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tipWindow = TooltipWindow(activity);
        initHeaders()
        loginViewModel.getAccessToken()
        loginViewModel.authenticateResponse.observe(this, {
            it?.let {
                if (it.isSuccess) {
                    Prefrences.storeUserId(activity!!, it.data.userId,it.data.picMedia.url)
                    loginViewModel.connectedUserId = Prefrences.getUserId(activity!!)?:""
                    showProgress()
                }else{
                    showToastMessage(it.error)
                }
            }
        })
        loginViewModel.loginResponse.observe(this,  {
            it?.let {
                hideProgress()
                setResult(Gson().toJson(it.data).toString())
                 if (it.isSuccess) {
                    Prefrences.storeApiToken(activity!!, it.data.userAccessToken)
                    loginViewModel.connectedUserId = Prefrences.getUserId(activity!!)?:""
                     activity!!.onBackPressed()
                 }else{
                    showToastMessage(it.error)
                }
            }
        })
        loginViewModel.showSignUpScreen.observe(this,  {
            if(it) showSignUpScreen(binding.root)
        })
        loginViewModel.showForgotPasswordScreen.observe(this,  {
          if(it){
              showForgotPasswordScreen(binding.root)
          }
        })
        loginViewModel.showProgress.observe(this,  {
            if(it){
               showProgress()
            }
        })
        loginViewModel.isLoginFormValidMediator.observe(this,  {

        })
        loginViewModel.showTermsOfServiceScreen.observe(this,  {
            if(it){
                showWebActivity(Constants.terms_of_service_url)
            }
        })
        loginViewModel.showPrivacyPolicyScreen.observe(this,  {
            if(it){
                showWebActivity(Constants.privacy_policy_url)
            }
        })
        binding.ivTooltip.setOnClickListener {
            tipWindow!!.showToolTip(it)
        }
    }

    private fun initHeaders() {
        loginViewModel.headers[NetworkRepository.SECRET_KEY] = Prefrences.getSecretKey(activity!!)
        loginViewModel.headers[NetworkRepository.SECRET_TOKEN] = Prefrences.getToken(activity!!)
    }
    private fun setResult(data:String){
        val intent = Intent()
        intent.putExtra(RESULT,data)
        requireActivity().setResult(Activity.RESULT_OK,intent)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        const val RESULT= "result"
    }
}