package com.example.nutritionanalysis.extention

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat

/**
 * Hides the soft input window.
 */

fun View.hideKeyboard() {
    val imm = (context.getSystemService(Context.INPUT_METHOD_SERVICE) ?: return)
            as InputMethodManager

    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Activity.hideSoftKeyboard() {
    var view: View? = currentFocus
    if (view == null) {
        view = View(this)
    }
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        view.windowToken,
        0
    )
    currentFocus?.let {
        val inputMethodManager =
            ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.hideInputType(view: View) {
    view.setOnTouchListener { it, _ ->
        if (it !is EditText) {
            it.hideKeyboard()
            hideSoftKeyboard()
        }
        false
    }
}