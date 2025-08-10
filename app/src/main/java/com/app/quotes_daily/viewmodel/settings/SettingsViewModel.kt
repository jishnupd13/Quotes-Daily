package com.app.quotes_daily.viewmodel.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.quotes_daily.data.local.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val settingsFlow = dataStoreManager.sampleSettingFlow

    fun toggleSetting(enabled: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setSampleSetting(enabled)
        }
    }
}