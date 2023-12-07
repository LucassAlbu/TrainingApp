package com.albuquerque.trainingapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.albuquerque.trainingapp.R
import com.albuquerque.trainingapp.databinding.RecoverFragmentBinding
import com.albuquerque.trainingapp.presentation.MainViewModel
import com.projetoFirebase.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoverFragment : Fragment() {


    private var _binding: RecoverFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecoverFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
        binding.btnSend.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        val email = binding.edtEmail.text.toString().trim()
        if (email.isEmpty()) {
            showBottomSheet(
                message = getString(R.string.recover_fragment_email_empty),
                titleDialog = getString(R.string.recover_fragment_bottom_sheet_title)
            )
        } else {
            showBottomSheet(message = getString(R.string.recover_fragment_recover_email),
                titleDialog = getString(R.string.recover_fragment_bottom_sheet_title),
                onClick = {
                    viewModel.recoverAccountFireBase(email)
                    findNavController().navigate(R.id.action_recoverFragment_to_loginFragment)
                })
        }
    }


}