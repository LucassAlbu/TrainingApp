package com.albuquerque.trainingapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.albuquerque.trainingapp.R
import com.albuquerque.trainingapp.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {


    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
        binding.tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverFragment)
        }

        binding.btnLogin.setOnClickListener {
            //TODO implementar quando login firebase estiver implementado
        }
    }

}