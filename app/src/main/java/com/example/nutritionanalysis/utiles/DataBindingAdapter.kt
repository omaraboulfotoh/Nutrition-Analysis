package com.example.nutritionanalysis.utiles

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.nutritionanalysis.R
import com.squareup.picasso.Picasso


@BindingAdapter("imageSrc")
fun ImageView.loadImage(imageSrc: Int) {
    Picasso.get()
        .load(imageSrc)
        .placeholder(R.mipmap.ic_launcher)
        .into(this)
}

@BindingAdapter("android:setTitle")
fun TextView.text(text: String) {
    this.text = text.replace("&amp;", "&")
}