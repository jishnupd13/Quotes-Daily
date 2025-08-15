package com.app.quotes_daily.ui.quotes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.quotes_daily.domain.model.quotes.Quotes
import com.app.quotes_daily.ui.theme.primaryColor
import com.app.quotes_daily.viewmodel.quotes.QuotesViewModel
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.text.font.FontWeight
import com.app.quotes_daily.ui.components.AppBar
import com.app.quotes_daily.ui.theme.AppFontFamily

@Composable
fun Quotes(
    viewModel: QuotesViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val favouriteMap by viewModel.favouriteMap.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(title = "Quotes")
        }
    ) { innerPaddingValues ->
        Column(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxSize(),
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
                            val isFav = item.id?.let { favouriteMap[it] } ?: false
                            QuotesItem(
                                quote = item,
                                isFavourite = isFav,
                                onFavouriteClick = { viewModel.onFavouriteToggle(item) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuotesItem(
    quote: Quotes,
    isFavourite: Boolean,
    onFavouriteClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
    ) {

        Row(
            modifier = Modifier
                .wrapContentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(14.dp)

            ) {
                Text(
                    text = quote.quote ?:"",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 15.sp
                    )
                )

                Spacer(modifier = Modifier.size(14.dp))

                Text(
                    text = quote.author?:"",
                    color = Color.DarkGray,
                    fontFamily = AppFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                )
            }

            val icon = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
            val tintColor = if (isFavourite) primaryColor else Color.Gray

            Image(
                imageVector = icon,
                contentDescription = "Favorite Item",
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 8.dp)
                    .clickable { onFavouriteClick() },
                colorFilter = ColorFilter.tint(tintColor)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuotesItemPreview(){
    QuotesItem(
        quote = Quotes(id = 1, quote = "Sample Quote", author = "Author Name"),
        isFavourite = false,
        onFavouriteClick = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuotesPreview(){
    Quotes()
}