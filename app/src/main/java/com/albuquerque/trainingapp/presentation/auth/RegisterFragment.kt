package com.albuquerque.trainingapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.albuquerque.trainingapp.R
import com.albuquerque.trainingapp.databinding.RegisterFragmentBinding
import com.albuquerque.trainingapp.presentation.MainViewModel
import com.albuquerque.trainingapp.presentation.UIState
import com.albuquerque.trainingapp.util.FirebaseHelper
import com.projetoFirebase.util.gone
import com.projetoFirebase.util.showBottomSheet
import com.projetoFirebase.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
        initObservables()
    }

    private fun initClicks() {
        binding.btnLogin.setOnClickListener {
            registerUser()

        }
    }

    private fun registerUser() {

        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        if (validateData(email, password)) {

            viewModel.uiStateSignUp.value = UIState.Loading
            viewModel.signUpFirebase(email, password)
        }
    }

    private fun validateData(
        email: String,
        password: String
    ): Boolean {
        val emailConfirmation = binding.edtConfirmEmail.text.toString().trim()
        val emailValid = isEmailValid(email)
        var emailIsEqual = false
        val passwordConfirmation = binding.edtConfirmPassword.text.toString().trim()
        var passwordIsEqual = false

        if (emailValid) {
            binding.tvEmailInvalid.gone()

        } else {
            binding.tvEmailInvalid.visible()
        }

        if (emailConfirmation.isEmpty()) {
            binding.tvEmailNotMatch.visible()

        } else {
            if (email == emailConfirmation) {

                binding.tvEmailNotMatch.gone()
                emailIsEqual = true

            } else {

                binding.tvEmailNotMatch.visible()
            }
        }
        if (password.isEmpty()) {
            binding.tvEmptyPassword.visible()
        } else {
            binding.tvEmptyPassword.gone()
        }
        if (password == passwordConfirmation) {
            binding.tvPasswordNotMatch.gone()
            passwordIsEqual = true
        } else {
            binding.tvPasswordNotMatch.visible()
        }
        return isEmailValid(email) && emailIsEqual && password.isNotEmpty() && passwordIsEqual

    }

    private fun uiStateManager(uiState: UIState) {
        when (uiState) {
            is UIState.AuthSuccess -> dismissLoading()
            is UIState.Loading -> setLoginLoading()
            is UIState.AuthError -> bottomSheetDialog(uiState.error, "Erro ao cadastrar")
            else -> return
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
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

    private fun dismissLoading() {
        binding.loading.gone()
        findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
    }

    private fun setLoginLoading() {
        binding.loading.visible()
    }

    private fun initObservables() {
        viewModel.uiStateSignUp.observe(requireActivity()) { uiStateSignUp ->
            uiStateManager(uiStateSignUp)
        }
    }
}