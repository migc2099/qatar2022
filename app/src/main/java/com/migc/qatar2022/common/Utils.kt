package com.migc.qatar2022.common

import android.app.Activity
import android.content.*
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.migc.qatar2022.BuildConfig
import java.io.File
import java.io.FileOutputStream
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

    fun getTempUriFromBitmap(context: Context, bitmap: Bitmap): Uri? {
        val imageName = "/knockouts.jpg"
        try {
            File(context.cacheDir, "images").deleteRecursively()
            val cachePath = File(context.cacheDir, "images")
            cachePath.mkdirs()
            val stream = FileOutputStream("$cachePath$imageName")
            bitmap.compress(
                Bitmap.CompressFormat.JPEG, 90, stream
            )
            stream.close()
        } catch (e: Exception) {
            Log.e("Utils", e.toString())
        }

        // SHARE
        val imagePath = File(context.cacheDir, "images")
        val newFile = File(imagePath, imageName)
        return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", newFile)
    }

    @Composable
    fun LockScreenOrientation(orientation: Int) {
        val context = LocalContext.current
        DisposableEffect(Unit) {
            val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
            val originalOrientation = activity.requestedOrientation
            activity.requestedOrientation = orientation
            onDispose {
                // restore original orientation when view disappears
                activity.requestedOrientation = originalOrientation
            }
        }
    }

    private fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }

    fun String.copyToClipboard(context: Context) {
        val clipBoard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("label", this)
        clipBoard.setPrimaryClip(clipData)
    }

}