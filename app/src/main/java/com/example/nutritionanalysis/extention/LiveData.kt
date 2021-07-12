package com.example.nutritionanalysis.extention

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


/**
 * This is a safe observer extension for [LiveData] to only receive non-null values.
 */
fun <T> Fragment.observe(liveData: LiveData<T>, block: (T) -> Unit) {
    liveData.observe(
        this.viewLifecycleOwner,
        Observer<T> {
            if (it != null) block.invoke(it)
        }
    )
}

fun <T> AppCompatActivity.observe(liveData: LiveData<T>, block: (T) -> Unit) {
    liveData.observe(
        this,
        Observer<T> {
            if (it != null) block.invoke(it)
        }
    )
}
