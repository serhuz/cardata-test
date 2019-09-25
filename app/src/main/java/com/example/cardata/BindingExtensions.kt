package com.example.cardata

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
fun RecyclerView.applyAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter("enabled")
fun View.applyEnabled(enabled: Boolean) {
    this.isEnabled = enabled
}

@BindingConversion
fun convertColorToDrawable(color: Int) = ColorDrawable(color)
