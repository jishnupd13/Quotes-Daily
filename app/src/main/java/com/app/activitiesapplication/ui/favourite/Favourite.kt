package com.app.activitiesapplication.ui.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.activitiesapplication.ui.quotes.QuotesItem
import com.app.activitiesapplication.viewmodel.favourite.FavouriteViewModel
import com.app.activitiesapplication.domain.model.quotes.Quotes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import com.app.activitiesapplication.ui.theme.primaryColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteScreen(
    viewModel: FavouriteViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(uiState){
        is FavouriteViewModel.FavouriteUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is FavouriteViewModel.FavouriteUiState.Success -> {
            val list = (uiState as FavouriteViewModel.FavouriteUiState.Success).favourites

            if(list.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    // Show empty message
                    androidx.compose.material3.Text(text = "No favourites yet")
                }
            }else{
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp)
                ){
                    items(list){ item ->
                        // Reuse QuotesItem with isFavourite true
                        QuotesItem(
                            quote = item,
                            isFavourite = true,
                            onFavouriteClick = { viewModel.onRemoveFavourite(item) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavouriteScreenPreview(){
    FavouriteScreenPreviewContent()
}

@Composable
private fun FavouriteScreenPreviewContent(){
    val sample = Quotes(id = 1, quote = "Sample Quote", author = "Author")
    Column(modifier = Modifier.fillMaxWidth().padding(12.dp)){
        QuotesItem(quote = sample, isFavourite = true, onFavouriteClick = {})
    }
}