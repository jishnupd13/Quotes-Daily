package com.app.quotes_daily.viewmodel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.quotes_daily.domain.model.states.States
import com.app.quotes_daily.utils.SPLASH_SCREEN_TIME_OUT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    init {
        setCountDown()
    }

    // This ViewModel can be used to handle any logic related to the splash screen.
    private val _loadingStateFlow = MutableStateFlow(States.LOADING)
    val loadingStateFlow = _loadingStateFlow

    fun setCountDown() = viewModelScope.launch{
        // This function can be used to set the loading state after a countdown or delay.
        // For example, you might want to change the state to SUCCESS after a certain time.
        delay(SPLASH_SCREEN_TIME_OUT)
        _loadingStateFlow.value = States.SUCCESS
    }
}