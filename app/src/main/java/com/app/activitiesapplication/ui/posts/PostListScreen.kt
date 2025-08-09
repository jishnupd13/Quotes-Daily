package com.app.activitiesapplication.ui.posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.activitiesapplication.viewmodel.posts.PostViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PostListScreen(onNavigateToSettings: () -> Unit, viewModel: PostViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Posts") }, actions = {
            IconButton(onClick = onNavigateToSettings) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        })
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when (uiState) {
                is PostViewModel.PostsUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is PostViewModel.PostsUiState.Error -> {
                    Text(text = (uiState as PostViewModel.PostsUiState.Error).message, modifier = Modifier.align(Alignment.Center))
                }

                is PostViewModel.PostsUiState.Success -> {
                    val posts = (uiState as PostViewModel.PostsUiState.Success).posts
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(posts) { post ->
                            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                                Text(text = post.title, style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}
