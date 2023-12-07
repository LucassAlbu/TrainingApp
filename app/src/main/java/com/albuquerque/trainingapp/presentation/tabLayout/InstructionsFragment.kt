package com.albuquerque.trainingapp.presentation.tabLayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.albuquerque.trainingapp.databinding.InstructionsFragmentBinding
import com.albuquerque.trainingapp.presentation.MainViewModel


class InstructionsFragment : Fragment() {

    private var _binding: InstructionsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = InstructionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}