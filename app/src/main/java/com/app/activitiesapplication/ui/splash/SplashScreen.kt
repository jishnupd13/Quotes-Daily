package com.app.activitiesapplication.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.activitiesapplication.R
import com.app.activitiesapplication.domain.model.states.States
import com.app.activitiesapplication.ui.theme.primaryColor
import com.app.activitiesapplication.ui.theme.whiteColor
import com.app.activitiesapplication.viewmodel.splash.SplashViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigateToHomePage: () -> Unit = {}
){

    val state by viewModel.loadingStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(state) {

        when(state){

            States.LOADING -> {
                // You can show a loading indicator here if needed
            }

            States.SUCCESS -> {
                // This is where you would navigate to the home page after the splash screen
                onNavigateToHomePage()
            }

            States.ERROR -> {
                // Handle error state if needed, e.g., show a message or retry option
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = primaryColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = whiteColor,
            fontSize = 32.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen()
}