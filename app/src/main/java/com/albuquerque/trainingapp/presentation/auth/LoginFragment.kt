package com.albuquerque.trainingapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.albuquerque.trainingapp.R
import com.albuquerque.trainingapp.databinding.LoginFragmentBinding
import com.albuquerque.trainingapp.presentation.MainViewModel
import com.albuquerque.trainingapp.presentation.UIState
import com.albuquerque.trainingapp.util.FirebaseHelper
import com.projetoFirebase.util.gone
import com.projetoFirebase.util.showBottomSheet
import com.projetoFirebase.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {


    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!


    private val viewModel: MainViewModel by activityViewModels()

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
        initObservables()
    }

    private fun initClicks() {
        binding.tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverFragment)
        }

        binding.btnLogin.setOnClickListener {
            validation()
        }
    }

    private fun validation() {

        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.tvEmptyEmail.visible()
        } else {
            binding.tvEmptyEmail.gone()
        }
        if (password.isEmpty()) {
            binding.tvEmptyPassword.visible()
        } else {
            binding.tvEmptyPassword.gone()

            viewModel.uiStateLogin.value = UIState.Loading
            viewModel.loginFireBase(email, password)
        }
    }

    private fun uiStateManager(uiState: UIState) {
        when (uiState) {
            is UIState.AuthSuccess -> dismissLoading()
            is UIState.Loading -> setFbLoginLoading()
            is UIState.AuthError -> bottomSheetDialog(uiState.error, "Erro ao cadastrar")
            else -> return

        }
    }

    private fun bottomSheetDialog(error: String, title: String) {
        showBottomSheet(
            message = getString(
                FirebaseHelper.validError(error)
            ),
            titleDialog = title
        )
        binding.loading.gone()
    }

    private fun setFbLoginLoading() {
        binding.loading.visible()
    }

    private fun dismissLoading() {
        binding.loading.gone()
        view?.post {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    private fun initObservables() {
        viewModel.uiStateLogin.observe(requireActivity()) { uiStateLogin ->
            uiStateManager(uiStateLogin)

        }
    }
}