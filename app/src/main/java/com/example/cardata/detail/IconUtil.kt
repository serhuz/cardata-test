package com.example.cardata.detail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes


fun Context.convertToBitmap(@DrawableRes drawableId: Int): Bitmap {
    val drawable = getDrawable(drawableId)!!

    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}
