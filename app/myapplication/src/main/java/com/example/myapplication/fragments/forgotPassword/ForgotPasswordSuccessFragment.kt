package com.example.myapplication.fragments.forgotPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.FragmentForgotPasswordBinding
import com.example.myapplication.databinding.FragmentForgotPasswordSuccessBinding
import com.example.myapplication.fragments.BaseFragment
import com.example.myapplication.storage.Prefrences

class ForgotPasswordSuccessFragment: BaseFragment() {
    private var _binding: FragmentForgotPasswordSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnForgotPassword.setOnClickListener {
            activity?.finish()
        }
    }
}