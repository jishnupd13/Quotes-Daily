package com.app.quotes_daily.domain.repository.quotes

import com.app.quotes_daily.domain.model.quotes.Quotes

interface QuotesRepository {
    suspend fun getQuotes(): List<Quotes>
}