package com.albuquerque.trainingapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.albuquerque.trainingapp.databinding.RecoverFragmentBinding

class RecoverFragment : Fragment() {


    private var _binding: RecoverFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecoverFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}