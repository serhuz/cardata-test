package com.example.cardata

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.appcompat.widget.ContentFrameLayout
import androidx.core.content.res.ResourcesCompat

class LoaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val contentView =
        ((context as Activity).window.decorView as FrameLayout).findViewById<ContentFrameLayout>(
            android.R.id.content
        )

    private val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

    init {
        View.inflate(context, R.layout.view_loader, this)
        setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.transparent_50,
                context.theme
            )
        )
        isClickable = true
        isFocusable = true
    }

    fun show() {
        hideKeyboard()
        contentView.indexOfChild(this)
            .takeIf { it == -1 }
            ?.let {
                contentView.addView(this, params)
                requestFocus()
            }
    }

    fun hide() {
        contentView.indexOfChild(this)
            .takeIf { it > -1 }
            ?.let { contentView.removeView(this) }
    }

    private fun hideKeyboard() {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
