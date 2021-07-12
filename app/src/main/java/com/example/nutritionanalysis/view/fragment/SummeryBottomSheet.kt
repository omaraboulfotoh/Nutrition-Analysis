package com.example.nutritionanalysis.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.nutritionanalysis.R
import com.example.nutritionanalysis.databinding.FragmentSummeryBinding
import com.example.nutritionanalysis.network.response.NutritionDetailsResponse
import com.example.nutritionanalysis.utiles.RoundedBottomSheetDialogFragment
import com.example.nutritionanalysis.view.adapter.SummeryAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummeryBottomSheet : RoundedBottomSheetDialogFragment() {

    private var _binding: FragmentSummeryBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    private val response: NutritionDetailsResponse by lazy {
        arguments?.getSerializable(DETAILS_KEY) as NutritionDetailsResponse
    }
    private val summeryAdapter: SummeryAdapter by lazy {
        SummeryAdapter()
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheet = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val bottomView = View.inflate(context, R.layout.fragment_summery, null)
        _binding = FragmentSummeryBinding.bind(bottomView)

        bottomSheet.setOnShowListener {
            bottomSheet.setContentView(binding.root)
            bottomSheetBehavior = BottomSheetBehavior.from(bottomView.parent as View)
            bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
            initView()
        }
        return bottomSheet
    }

    private fun initView() {

        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.rvSummery.run {
            adapter = summeryAdapter
            addItemDecoration(divider)
        }
    }

    companion object {
        private const val DETAILS_KEY = "SummeryBottomSheet.NutritionDetails"
        fun newInstance(response: NutritionDetailsResponse): SummeryBottomSheet {
            val fragment = SummeryBottomSheet()
            fragment.arguments = Bundle().apply {
                putSerializable(DETAILS_KEY, response)
            }
            return fragment
        }
    }
}