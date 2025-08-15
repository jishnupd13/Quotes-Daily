package com.app.quotes_daily.ui.daily_quote

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.quotes_daily.ui.components.AppBar
import com.app.quotes_daily.viewmodel.daily_quote.DailyQuoteViewModel
import kotlin.math.max

@Composable
fun DailyQuoteScreen(
    viewModel: DailyQuoteViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(title = "Daily Quote")
        }
    ) { innerPaddingValues->

        Column(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxSize(),
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
                    QuotesTile(
                        quote = data.quote,
                        author = data.author,
                        scrollState = scrollState
                    )
                }
            }
        }
    }
}

@Composable
fun QuotesTile(
    quote: String,
    author: String,
    scrollState: ScrollState
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .verticalScroll(scrollState)
    ) {
        Text(
            text = quote,
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic,
            lineHeight = 1.5.em,
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.size(14.dp))

        Text(
            text = author,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 14.dp),
            textAlign = TextAlign.End,
            maxLines = 2

        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuotesTilePreview(){
    QuotesTile(
        quote = "Do or Die",
        author = "Franklin D. Roosevelt",
        scrollState = rememberScrollState()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DailyQuoteScreenPreview(){
    DailyQuoteScreen()
}
