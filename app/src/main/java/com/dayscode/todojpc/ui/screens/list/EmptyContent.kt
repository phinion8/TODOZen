package com.dayscode.todojpc.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dayscode.todojpc.R
import com.dayscode.todojpc.ui.theme.MediumGray

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = "Sad Face Icon",
            tint = MediumGray
        )
        Text(
            text = "No Tasks Found.",
            fontWeight = FontWeight.Bold,
            color = MediumGray,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }
}