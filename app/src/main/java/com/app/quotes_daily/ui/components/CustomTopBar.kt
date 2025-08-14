package com.app.quotes_daily.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.app.quotes_daily.ui.theme.ActivitiesApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String
){
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 14.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview(
    title: String = "Title"
){
    ActivitiesApplicationTheme{
        AppBar(title)
    }
}