package com.app.quotes_daily.ui.daily_quote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DailyQuoteScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Do or Die",
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.size(14.dp))

        Text(
            text = "Mahatma Gandhi",
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DailyQuoteScreenPreview(){
    DailyQuoteScreen()
}
