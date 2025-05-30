package com.example.qweather.utils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.compose.ui.text.style.TextDirection
import java.util.Locale

object LocaleUtils {
    const val ENGLISH = "en"
    const val ARABIC = "ar"

    fun setLocale(context: Context, languageCode: String) {
        val locale = when (languageCode) {
            ARABIC -> Locale("ar")
            else -> Locale("en")
        }
        
        Locale.setDefault(locale)
        
        val config = Configuration(context.resources.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
        } else {
            @Suppress("DEPRECATION")
            config.locale = locale
        }
        
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun getTextDirection(languageCode: String): TextDirection {
        return when (languageCode) {
            ARABIC -> TextDirection.Rtl
            else -> TextDirection.Ltr
        }
    }

    fun getLayoutDirection(languageCode: String): androidx.compose.ui.unit.LayoutDirection {
        return when (languageCode) {
            ARABIC -> androidx.compose.ui.unit.LayoutDirection.Rtl
            else -> androidx.compose.ui.unit.LayoutDirection.Ltr
        }
    }
} 