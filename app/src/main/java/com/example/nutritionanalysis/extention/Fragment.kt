package com.example.nutritionanalysis.extention

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Fragment.toast(msg: String?) {
    if (msg.isNullOrBlank()) {
        return
    }

    lifecycleScope.launch(Dispatchers.Main) {
        Toast.makeText(this@toast.requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}

fun Fragment.backPressed() = findNavController().popBackStack()

fun Fragment.hideInputType(view: View) {
    view.setOnTouchListener(View.OnTouchListener { it, _ ->
        if (it !is EditText) {
            it.hideKeyboard()
            hideSoftKeyboard()
        }
        false
    })
}

fun Fragment.hideSoftKeyboard() {

    var view: View? = activity?.currentFocus
    if (view == null) {
        view = View(context)
    }
    (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        view.windowToken,
        0
    )

    activity?.currentFocus?.let {
        val inputMethodManager =
            context?.let { it1 ->
                ContextCompat.getSystemService(
                    it1,
                    InputMethodManager::class.java
                )
            }!!
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

