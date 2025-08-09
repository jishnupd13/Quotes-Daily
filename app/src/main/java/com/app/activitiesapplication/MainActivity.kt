package com.app.activitiesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import com.app.activitiesapplication.ui.theme.ActivitiesApplicationTheme
import com.app.activitiesapplication.ui.navigation.AppNavGraph
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivitiesApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavGraph()
                }
            }
        }
    }
}