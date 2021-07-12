package com.example.nutritionanalysis.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nutritionanalysis.R
import com.example.nutritionanalysis.databinding.FragmentHomeBinding
import com.example.nutritionanalysis.extention.observe
import com.example.nutritionanalysis.network.request.IngrRequest
import com.example.nutritionanalysis.viewmodel.NutritionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NutritionViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            submit(binding.etIngrs.text.toString().split("\n") as MutableList<String>)
        }

    }

    private fun submit(ingr: MutableList<String>) = with(viewModel) {
        observe(loadDetails(IngrRequest(ingr))) {
            it?.let { response ->
                val args = bundleOf(
                    getString(R.string.DETAILS_KEY) to response
                )
                findNavController().navigate(R.id.action_homeFragment_to_summeryFragment, args)
            }

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}