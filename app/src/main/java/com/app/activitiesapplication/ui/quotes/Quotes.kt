package com.app.activitiesapplication.ui.quotes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
import com.app.activitiesapplication.domain.model.quotes.Quotes
import com.app.activitiesapplication.viewmodel.quotes.QuotesViewModel

@Composable
fun Quotes(
    viewModel: QuotesViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when(uiState){
            is QuotesViewModel.QuotesUiState.Loading -> {
                CircularProgressIndicator()
            }
            is QuotesViewModel.QuotesUiState.Error -> {
                Text(
                    text = "Oops! Something went wrong...",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
            is QuotesViewModel.QuotesUiState.Success -> {
                val quotes = (uiState as QuotesViewModel.QuotesUiState.Success).quotes
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp)
                ) {
                    items(quotes){ item->
                        QuotesItem(quote = item)
                    }
                }
            }
        }

    }
}

@Composable
fun QuotesItem(
    quote: Quotes
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = quote.quote ?:"",
                color = Color.Black
            )

            Spacer(modifier = Modifier.size(14.dp))

            Text(
                text = quote.author?:"",
                color = Color.Black,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuotesItemPreview(){
    QuotesItem(
        quote = Quotes(id = 1, quote = "Sample Quote", author = "Author Name")
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuotesPreview(){
    Quotes()
}