package com.example.myapplication.fragments.forgotPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.FragmentForgotPasswordBinding
import com.example.myapplication.fragments.BaseFragment
import com.example.myapplication.storage.Prefrences

class ForgotPasswordFragment: BaseFragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private val forgotPasswordViewModel: ForgotViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        binding.forgotViewModel=forgotPasswordViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forgotPasswordViewModel.forgotPasswordResponse.observe(this, Observer {
            it?.let {
                hideProgress()
                if (it.isSuccess) {
                    showForgotPasswordSucessScreen(binding.root)
                }else{
                    showToastMessage(it.error)
                }
            }
        })
        forgotPasswordViewModel.showLoginScreen.observe(this, Observer {
            if(it){
                showLoginScreen(binding.root)
            }
        })
        forgotPasswordViewModel.getResetLink.observe(this, Observer {
            if(it){
              forgotPasswordViewModel.forgotPassword()
            }
        })
        forgotPasswordViewModel.showProgress.observe(this, Observer {
            if(it){
                showProgress()
            }
        })
    }
}