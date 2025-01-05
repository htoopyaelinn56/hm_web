package org.may.hmweb

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun getContainerColor(): Color {
    return if (isSystemInDarkTheme()) Color.Black
    else Color.White
}

@Composable
fun getItemColor(): Color {
    return if (isSystemInDarkTheme()) Color.White
    else Color.Black
}

@Composable
fun getButtonForeground(): Color {
    return Color.White
}

@Composable
fun getButtonBackground(): Color {
    return if (isSystemInDarkTheme()) Color(0xff74727a)
    else Color(0xff4614ba)
}

fun formatWithCommas(number: Number): String {
    // Split the float into whole and decimal parts
    val parts = number.toString().split(".")
    val wholePart = parts[0]
    val decimalPart = if (parts.size > 1) "." + parts[1] else ""

    // Format the whole part with commas
    val formattedWholePart = wholePart.reversed().chunked(3).joinToString(",").reversed()

    return formattedWholePart
}

fun getRandomColor(): Color {
    val colors = listOf(
        Color(0xff0051ff),
        Color(0xffffbb00),
        Color(0xff00fff2),
        Color(0xff5f05f0),
        Color(0xfff005cc),
        Color(0xfffa0036),
        Color(0xfffa0036),
    )
    return colors[Random.nextInt(colors.size)]
}