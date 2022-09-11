package com.migc.qatar2022.common

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun Long.toTimeDateString(): String {
        val dateTime = Date(this)
        val format = SimpleDateFormat("EEE, MMM dd - HH:mm", Locale.US)
        return format.format(dateTime)
    }

}