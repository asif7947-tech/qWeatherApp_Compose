package com.example.qweather.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qweather.ui.theme.AppColorScheme
import com.example.qweather.ui.theme.AppShape
import com.example.qweather.ui.theme.AppSize
import com.example.qweather.ui.theme.AppTypography
import com.example.qweather.ui.theme.LocalAppColorScheme
import com.example.qweather.ui.theme.LocalAppShape
import com.example.qweather.ui.theme.LocalAppSize
import com.example.qweather.ui.theme.LocalAppTypography

// set dark color scheme
val darkColorScheme = AppColorScheme(
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = Color.White,
    onSecondary = Color.Black,
    error = Color.Red,
    onError = Color.White,
    text = Color.White,
    appBarColor = Color.Black,
    tabBackground = Color.Black,
    tabSelectedColor = Color.White,
    tabUnSelectedColor = Color.White,
    tabContentColor = Color.White,
    tabIndicatorBgColor = tabIndicatorBgColorDark,
    appStatusBarColor = Color.Black,
    appBackground = appBackground,
    subscription_button_color = subscription_button_color,
    subscription_button_text_color = subscription_button_text_color,
    app_text_color = app_text_color,
    app_status_bar_color = app_status_bar_color,
    app_button_color = app_button_color,
    app_background_color = app_background_color,
    appBackgroundNavColor = app_background_color_2

)


// set light color scheme
val lightColorScheme = AppColorScheme(
    background = appBackground,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    primary = primary,
    onPrimary = onPrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    error = Color.Red,
    onError = Color.Black,
    text = white,
    appBarColor = appBarColor,
    tabBackground = tabBackground,
    tabSelectedColor = tabSelectedColor,
    tabUnSelectedColor = tabUnSelectedColor,
    tabContentColor = tabContentColor,
    tabIndicatorBgColor = tabIndicatorBgColorLight,
    appStatusBarColor = appStatusBarColor,
    subscription_button_color = subscription_button_color,
    subscription_button_text_color = subscription_button_text_color,
    app_text_color = app_text_color,
    app_status_bar_color = app_status_bar_color,
    app_button_color = app_button_color,
    app_background_color = app_background_color,
    appBackground = appBackground,
    appBackgroundNavColor = app_background_color_2
)


// set app typography
val typography = AppTypography(
    titleNormal = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    body = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    button = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    caption = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    subtitle = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    headline = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    heading = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp
    ),
    label = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    tabHeading = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    navBarTitle = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    tabTitle = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    appMainHeading = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    appSubHeading = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    soundCardTitle = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
    ),
    pageMainHeading = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    pacificRegular = TextStyle(
        fontFamily = pacificoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    filterChipTitle = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    pacificLarge = TextStyle(
        fontFamily = pacificoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    appButton_text = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    subcription_option_title_text = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
    ),
    soundTimerText = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    soundDetailTimerText = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 40.sp
    ),
    playingCardHeading = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),
    playingCardSubHeading = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
    ),
    playingCardTitle = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
)

// set app shapes
val shapes = AppShape(
    container = RoundedCornerShape(16.dp),
    button = RoundedCornerShape(50.dp),
    card = RoundedCornerShape(12.dp),
    dialog = RoundedCornerShape(22.dp),
    tab = RoundedCornerShape(12.dp),
)

// set app dimensions
val sizes = AppSize(
    small = 8.dp,
    normal = 16.dp,
    medium = 24.dp,
    large = 32.dp,
    extraLarge = 64.dp
)


@Composable
fun QWeatherAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme
    val rippleIndication = ripple()
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shapes,
        LocalAppSize provides sizes,
        LocalIndication provides rippleIndication,
        content = content
    )
}


object MyAppTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current
    val typography: AppTypography
        @Composable get() = LocalAppTypography.current
    val shape: AppShape
        @Composable get() = LocalAppShape.current
    val size: AppSize
        @Composable get() = LocalAppSize.current
}
