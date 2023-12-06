package com.albuquerque.trainingapp.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.albuquerque.trainingapp.R
import com.albuquerque.trainingapp.databinding.SplashFragmentBinding

class SplashFragment : Fragment() {


    private var _binding: SplashFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)

        Handler(Looper.getMainLooper()).postDelayed(
            { findNavController().navigate(R.id.action_splashFragment_to_loginFragment) },
            3000
        )

        return binding.root
    }

//    override fun onStart() {
//        super.onStart()
//
//        val currentUser = viewModel.currentUser
//        if (currentUser != null) {
//            Handler(Looper.getMainLooper()).postDelayed(
//                { findNavController().navigate(R.id.action_splashFragment_to_homeFragment) },
//                3000
//            )
//
//        } else {
//            Handler(Looper.getMainLooper()).postDelayed(
//                { findNavController().navigate(R.id.action_splashFragment_to_loginFragment) },
//                3000
//            )
//        }
//    }
}