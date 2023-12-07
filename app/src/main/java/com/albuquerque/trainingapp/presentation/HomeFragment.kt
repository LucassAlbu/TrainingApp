package com.albuquerque.trainingapp.presentation

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.albuquerque.trainingapp.R
import com.albuquerque.trainingapp.adapter.TabLayoutAdapter
import com.albuquerque.trainingapp.databinding.HomeFragmentBinding
import com.albuquerque.trainingapp.presentation.tabLayout.HistoricFragment
import com.albuquerque.trainingapp.presentation.tabLayout.InstructionsFragment
import com.albuquerque.trainingapp.presentation.tabLayout.TrainingFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configTablayout()
    }

    private fun configTablayout() {
        val pageAdapter = TabLayoutAdapter(requireActivity())
        binding.viewPager.adapter = pageAdapter

        pageAdapter.addFragment(TrainingFragment(), R.string.training_fragment)
        pageAdapter.addFragment(HistoricFragment(), R.string.historic_fragment)
        pageAdapter.addFragment(InstructionsFragment(), R.string.instruction_fragment)

        binding.viewPager.offscreenPageLimit = pageAdapter.itemCount

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(pageAdapter.getTitle(position))
        }.attach()
    }


}