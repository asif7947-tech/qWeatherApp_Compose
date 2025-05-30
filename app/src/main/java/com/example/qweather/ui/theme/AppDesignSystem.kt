package com.example.qweather.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp


// colors
data class AppColorScheme(
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val error: Color,
    val onError: Color,
    val text: Color,
    val appBarColor: Color,
    val tabBackground: Color,
    val tabSelectedColor: Color,
    val tabUnSelectedColor: Color,
    val tabContentColor: Color,
    val tabIndicatorBgColor: Color,
    val appStatusBarColor: Color,
    val appBackground: Color,
    val subscription_button_color: Color,
    val subscription_button_text_color: Color,
    val app_text_color: Color,
    val app_status_bar_color: Color,
    val app_button_color: Color,
    val app_background_color: Color,
    val appBackgroundNavColor: Color
)

// typography

data class AppTypography(
    val appMainHeading: TextStyle,
    val appSubHeading: TextStyle,
    val titleNormal: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val subtitle: TextStyle,
    val body: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
    val headline: TextStyle,
    val heading: TextStyle,
    val label: TextStyle,
    val labelLarge: TextStyle,
    val labelSmall: TextStyle,
    val tabHeading: TextStyle,
    val navBarTitle: TextStyle,
    val tabTitle: TextStyle,
    val pacificRegular: TextStyle,
    val pageMainHeading: TextStyle,
    val filterChipTitle: TextStyle,
    val pacificLarge: TextStyle,
    val appButton_text: TextStyle,
    val subcription_option_title_text: TextStyle,
    val soundCardTitle: TextStyle,
    val soundTimerText: TextStyle,
    val soundDetailTimerText: TextStyle,
    val playingCardHeading: TextStyle,
    val playingCardSubHeading: TextStyle,
    val playingCardTitle: TextStyle
)

//  shapes

data class AppShape(
    val container: Shape,
    val button: Shape,
    val card: Shape,
    val dialog: Shape,
    val tab: Shape,
)

// sizes

data class AppSize(
    val normal: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp,
)

//  dimensions
data class AppDimensions(
    val padding: AppSize,
    val elevation: AppSize,
    val cornerRadius: AppSize,
)

//  theme
data class AppTheme(
    val colors: AppColorScheme,
    val typography: AppTypography,
    val shapes: AppShape,
    val dimensions: AppDimensions,
    val sizes: AppSize
)

//  app theme
val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        surface = Color.Unspecified,
        onSurface = Color.Unspecified,
        error = Color.Unspecified,
        onError = Color.Unspecified,
        text = Color.Unspecified,
        appBarColor = Color.Unspecified,
        tabBackground = Color.Unspecified,
        tabSelectedColor = Color.Unspecified,
        tabUnSelectedColor = Color.Unspecified,
        tabContentColor = Color.Unspecified,
        tabIndicatorBgColor = Color.Unspecified,
        appStatusBarColor = Color.Unspecified,
        subscription_button_color = Color.Unspecified,
        subscription_button_text_color = Color.Unspecified,
        app_text_color = Color.Unspecified,
        app_status_bar_color = Color.Unspecified,
        app_button_color = Color.Unspecified,
        app_background_color = Color.Unspecified,
        appBackground = Color.Unspecified,
        appBackgroundNavColor = Color.Unspecified
    )
}


val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleNormal = TextStyle.Default,
        titleLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        subtitle = TextStyle.Default,
        body = TextStyle.Default,
        button = TextStyle.Default,
        caption = TextStyle.Default,
        headline = TextStyle.Default,
        heading = TextStyle.Default,
        label = TextStyle.Default,
        tabHeading = TextStyle.Default,
        navBarTitle = TextStyle.Default,
        tabTitle = TextStyle.Default,
        appMainHeading = TextStyle.Default,
        appSubHeading = TextStyle.Default,
        pacificRegular = TextStyle.Default,
        labelLarge = TextStyle.Default,
        labelSmall = TextStyle.Default,
        pageMainHeading = TextStyle.Default,
        filterChipTitle = TextStyle.Default,
        pacificLarge = TextStyle.Default,
        appButton_text = TextStyle.Default,
        subcription_option_title_text = TextStyle.Default,
        soundCardTitle = TextStyle.Default,
        soundTimerText = TextStyle.Default,
        soundDetailTimerText = TextStyle.Default,
        playingCardHeading = TextStyle.Default,
        playingCardSubHeading = TextStyle.Default,
        playingCardTitle = TextStyle.Default
    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        container = RectangleShape,
        button = RectangleShape,
        card = RectangleShape,
        dialog = RectangleShape,
        tab = RectangleShape,
    )
}

val LocalAppDimensions = staticCompositionLocalOf {
    AppDimensions(
        padding = AppSize(
            normal = Dp.Unspecified,
            small = Dp.Unspecified,
            medium = Dp.Unspecified,
            large = Dp.Unspecified,
            extraLarge = Dp.Unspecified,
        ),
        elevation = AppSize(
            normal = Dp.Unspecified,
            small = Dp.Unspecified,
            medium = Dp.Unspecified,
            large = Dp.Unspecified,
            extraLarge = Dp.Unspecified,
        ),
        cornerRadius = AppSize(
            normal = Dp.Unspecified,
            small = Dp.Unspecified,
            medium = Dp.Unspecified,
            large = Dp.Unspecified,
            extraLarge = Dp.Unspecified,
        )
    )

}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        normal = Dp.Unspecified,
        small = Dp.Unspecified,
        medium = Dp.Unspecified,
        large = Dp.Unspecified,
        extraLarge = Dp.Unspecified,
    )
}