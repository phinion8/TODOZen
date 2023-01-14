package com.dayscode.todojpc.ui.theme

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LowPriorityColor = Color(0xFF00c980)
val MediumPriorityColor = Color(0xFFFFC107)
val HighPriorityColor = Color(0xFFF44336)
val NonePriorityColor = Color(0xFF8D8D8D)
val LightGray = Color(0xFFEBEBEB)
val MediumGray = Color(0xFF8D8D8D)
val DarkGray = Color(0xFF2E2E2E)

val androidx.compose.material.Colors.taskItemColor: Color
@Composable
get() = if (!isSystemInDarkTheme()) DarkGray else Color.White


val androidx.compose.material.Colors.taskItemBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.White else background

val androidx.compose.material.Colors.topAppBarContentColor: Color
@Composable
get() = if (!isSystemInDarkTheme()) Color.White else LightGray

val androidx.compose.material.Colors.topAppBarBackgroundColor: Color
@Composable
get() = if (isSystemInDarkTheme()) Color.Black else Purple500

