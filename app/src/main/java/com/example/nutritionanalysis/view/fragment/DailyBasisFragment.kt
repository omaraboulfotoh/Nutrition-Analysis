package com.example.nutritionanalysis.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.nutritionanalysis.R
import com.example.nutritionanalysis.databinding.FragmentDailyBasisBinding
import com.example.nutritionanalysis.extention.observe
import com.example.nutritionanalysis.network.response.NutritionDetailsResponse
import com.example.nutritionanalysis.view.adapter.DailyBasisAdapter
import com.example.nutritionanalysis.viewmodel.NutritionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyBasisFragment : Fragment() {

    private var _binding: FragmentDailyBasisBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NutritionViewModel by viewModels()

    private val response: NutritionDetailsResponse by lazy {
        arguments?.getSerializable(getString(R.string.DETAILS_KEY)) as NutritionDetailsResponse
    }
    private val dailyBasisAdapter: DailyBasisAdapter by lazy {
        DailyBasisAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDailyBasisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        bindViewModel()
    }


    private fun initView() {
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.rvDailyBasis.run {
            adapter = dailyBasisAdapter
            addItemDecoration(divider)
        }

        binding.toolbar.getChildAt(0).setOnClickListener { findNavController().popBackStack() }
        binding.details = response
        binding.executePendingBindings()
    }

    private fun bindViewModel() = with(viewModel) {
        observe(getDailyBasis(response)) {
            dailyBasisAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}