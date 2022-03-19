package com.example.myapplication.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentRegisterBinding
import com.example.myapplication.fragments.BaseFragment
import com.example.myapplication.storage.Prefrences
import com.example.myapplication.utils.Constants
import com.example.myapplication.utils.TooltipWindow

class RegisterFragment: BaseFragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val registerViewModel: RegisterViewModel by viewModels()
    private var tipWindow: TooltipWindow?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.registerViewModel=registerViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerViewModel.connectedUserId = Prefrences.getUserId(activity!!)?:""
        tipWindow = TooltipWindow(activity);
        registerViewModel.registerResponse.observe(this,  {
            it?.let {
                hideProgress()
                if (it.isSuccess) {
                  ///  Prefrences.storeApiToken(activity!!, it.data.token)
                    showRegisterSucessScreen(binding.root)
                }else{
                    showToastMessage(it.error)
                }
            }
        })
        registerViewModel.showLoginScreen.observe(this,  {
            if(it){
                showLoginScreen(binding.root)
            }
        })
        registerViewModel.showForgotPasswordScreen.observe(this,  {
            if(it){
              showForgotPasswordScreen(binding.root)
            }
        })
        registerViewModel.showProgress.observe(this,  {
            if(it){
                showProgress()
            }
        })
        registerViewModel.showTermsOfServiceScreen.observe(this,  {
            if(it){
                showWebActivity(Constants.terms_of_service_url)
            }
        })
        registerViewModel.showPrivacyPolicyScreen.observe(this,  {
            if(it){
                showWebActivity(Constants.privacy_policy_url)
            }
        })
        binding.ivTooltip.setOnClickListener {
            tipWindow!!.showToolTip(binding.ivTooltip)
        }
    }
}