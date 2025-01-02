package org.may.hmweb

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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
    return number.toString()
}
