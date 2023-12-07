package com.albuquerque.trainingapp.presentation.tabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.albuquerque.trainingapp.R
import com.albuquerque.trainingapp.databinding.HistoricFragmentBinding
import com.albuquerque.trainingapp.presentation.MainViewModel

class HistoricFragment : Fragment() {

    private var _binding: HistoricFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HistoricFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}