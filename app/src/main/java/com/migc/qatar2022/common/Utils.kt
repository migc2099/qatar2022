package com.migc.qatar2022.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun Long.toTimeDateString(): String {
        val dateTime = Date(this)
        val format = SimpleDateFormat("EEE, MMM dd - HH:mm", Locale.US)
        return format.format(dateTime)
    }

    fun getBitmap(context: Context, drawableRes: Int): Bitmap? {
        val drawable: Drawable = context.resources.getDrawable(drawableRes, null)
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }

}