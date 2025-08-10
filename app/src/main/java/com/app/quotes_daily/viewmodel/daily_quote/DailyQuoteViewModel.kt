package com.app.quotes_daily.viewmodel.daily_quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import javax.inject.Inject

@HiltViewModel
class DailyQuoteViewModel @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : ViewModel() {

    private val _uiState = MutableStateFlow<DailyQuoteUiState>(DailyQuoteUiState.Loading)
    val uiState: StateFlow<DailyQuoteUiState> = _uiState.asStateFlow()

    init {
        fetchDailyQuote()
    }

    private fun fetchDailyQuote() {
        viewModelScope.launch {
            try {
                remoteConfig.fetchAndActivate().addOnCompleteListener {
                    val jsonString = remoteConfig.getString("daily_quote")
                    runCatching {
                        val element = Json.parseToJsonElement(jsonString)
                        val obj = element.jsonObject
                        val quote = obj["quote"]?.jsonPrimitive?.content ?: ""
                        val author = obj["author"]?.jsonPrimitive?.content ?: ""
                        _uiState.value = DailyQuoteUiState.Success(quote, author)
                    }.getOrElse {
                        _uiState.value = DailyQuoteUiState.Error("Parsing error")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = DailyQuoteUiState.Error(e.localizedMessage ?: "Error fetching quote")
            }
        }
    }

    sealed interface DailyQuoteUiState {
        data object Loading : DailyQuoteUiState
        data class Success(val quote: String, val author: String) : DailyQuoteUiState
        data class Error(val message: String) : DailyQuoteUiState
    }
}
