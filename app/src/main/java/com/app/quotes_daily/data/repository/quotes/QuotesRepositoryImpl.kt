package com.app.quotes_daily.data.repository.quotes

import com.app.quotes_daily.data.remote.quotes.QuotesApiService
import com.app.quotes_daily.data.remote.quotes.toQuote
import com.app.quotes_daily.domain.model.quotes.Quotes
import com.app.quotes_daily.domain.repository.quotes.QuotesRepository

class QuotesRepositoryImpl(
    private val api: QuotesApiService
): QuotesRepository {
    override suspend fun getQuotes(): List<Quotes>  = api.getQuotes().map { it.toQuote() }
}