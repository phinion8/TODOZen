package com.dayscode.todojpc.data.models

import androidx.compose.ui.graphics.Color
import com.dayscode.todojpc.ui.theme.HighPriorityColor
import com.dayscode.todojpc.ui.theme.LowPriorityColor
import com.dayscode.todojpc.ui.theme.MediumPriorityColor
import com.dayscode.todojpc.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}