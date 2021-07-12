package com.example.nutritionanalysis.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.nutritionanalysis.databinding.FragmentSummeryBinding
import com.example.nutritionanalysis.extention.observe
import com.example.nutritionanalysis.network.response.NutritionDetailsResponse
import com.example.nutritionanalysis.view.adapter.SummeryAdapter
import com.example.nutritionanalysis.viewmodel.NutritionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummeryFragment : Fragment() {

    private var _binding: FragmentSummeryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NutritionViewModel by viewModels()

    private val response: NutritionDetailsResponse by lazy {
        arguments?.getSerializable(DETAILS_KEY) as NutritionDetailsResponse
    }
    private val summeryAdapter: SummeryAdapter by lazy {
        SummeryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummeryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        bindViewModel()
    }


    private fun initView() {

        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.rvSummery.run {
            adapter = summeryAdapter
            addItemDecoration(divider)
        }
        binding.toolbar.getChildAt(0).setOnClickListener {   findNavController().popBackStack() }
        binding.btnContinue.setOnClickListener {

        }
    }

    private fun bindViewModel() = with(viewModel) {
        observe(getSummery(response)) {
            summeryAdapter.submitList(it)
        }
    }

    companion object {
        private const val DETAILS_KEY = "SummeryBottomSheet.NutritionDetails"
        fun newInstance(response: NutritionDetailsResponse): SummeryFragment {
            val fragment = SummeryFragment()
            fragment.arguments = Bundle().apply {
                putSerializable(DETAILS_KEY, response)
            }
            return fragment
        }
    }
}