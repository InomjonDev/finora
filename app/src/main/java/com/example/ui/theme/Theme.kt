package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = ForestGreenPrimary,
    secondary = OrangeSecondary,
    tertiary = SlateGrayTertiary,
    background = BackgroundColor,
    surface = SurfaceColor,
    onPrimary = OnForestGreenPrimary,
    onSecondary = OnOrangeSecondary,
    onTertiary = OnSlateGrayTertiary,
    onBackground = OnBackgroundColor,
    onSurface = OnSurfaceColor,
    outline = OutlineColor,
    outlineVariant = OutlineVariantColor
  )

private val LightColorScheme =
  lightColorScheme(
    primary = ForestGreenPrimary,
    secondary = OrangeSecondary,
    tertiary = SlateGrayTertiary,
    background = BackgroundColor,
    surface = SurfaceColor,
    onPrimary = OnForestGreenPrimary,
    onSecondary = OnOrangeSecondary,
    onTertiary = OnSlateGrayTertiary,
    onBackground = OnBackgroundColor,
    onSurface = OnSurfaceColor,
    surfaceVariant = SurfaceVariantColor,
    onSurfaceVariant = OnSurfaceVariantColor,
    outline = OutlineColor,
    outlineVariant = OutlineVariantColor
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Use custom brand styling instead of system dynamic color
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
