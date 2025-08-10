package com.app.quotes_daily.ui.daily_quote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.quotes_daily.viewmodel.daily_quote.DailyQuoteViewModel

@Composable
fun DailyQuoteScreen(
    viewModel: DailyQuoteViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is DailyQuoteViewModel.DailyQuoteUiState.Loading -> {
                CircularProgressIndicator()
            }
            is DailyQuoteViewModel.DailyQuoteUiState.Error -> {
                Text(text = (state as DailyQuoteViewModel.DailyQuoteUiState.Error).message)
            }
            is DailyQuoteViewModel.DailyQuoteUiState.Success -> {
                val data = state as DailyQuoteViewModel.DailyQuoteUiState.Success
                Text(
                    text = data.quote,
                    fontSize = 32.sp,
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.size(14.dp))

                Text(
                    text = data.author,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DailyQuoteScreenPreview(){
    DailyQuoteScreen()
}
