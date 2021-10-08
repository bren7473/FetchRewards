package com.example.fetchrewardstest.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("my_number")
fun setNumber(view: TextView, value: Int?) {
    if (!view.text.equals(value))
        view.text = "" + value
}

@InverseBindingAdapter(attribute = "my_number")
fun getNumber(view: TextView): Int {
    return view.text.toString().toIntOrNull() ?: 0
}