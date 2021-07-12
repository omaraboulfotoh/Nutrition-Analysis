package com.example.nutritionanalysis.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.nutritionanalysis.R
import com.example.nutritionanalysis.databinding.FragmentDailyBasisBinding
import com.example.nutritionanalysis.network.response.NutritionDetailsResponse
import com.example.nutritionanalysis.view.adapter.SummeryAdapter
import com.example.nutritionanalysis.viewmodel.NutritionViewModel

class DailyBasisFragment : Fragment() {

    private var _binding: FragmentDailyBasisBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NutritionViewModel by viewModels()

    private val response: NutritionDetailsResponse by lazy {
        arguments?.getSerializable(getString(R.string.DETAILS_KEY)) as NutritionDetailsResponse
    }
    private val summeryAdapter: SummeryAdapter by lazy {
        SummeryAdapter()
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

    private fun bindViewModel() = with(viewModel) {

    }

    private fun initView() {

    }

}