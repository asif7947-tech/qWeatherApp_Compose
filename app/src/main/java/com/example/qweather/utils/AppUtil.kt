package com.example.qweather.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AppUtil {


    fun formatTimestamp(timestampInSeconds: Long): String {
        val date = Date(timestampInSeconds * 1000) // Convert to milliseconds
        val sdf = SimpleDateFormat("EEE, dd MMM, hh:mm a", Locale.getDefault())
        return sdf.format(date)
    }
}