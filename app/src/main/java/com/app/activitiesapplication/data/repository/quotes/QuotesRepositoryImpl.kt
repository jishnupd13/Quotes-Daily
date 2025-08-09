package com.app.activitiesapplication.data.repository.quotes

import com.app.activitiesapplication.data.remote.quotes.QuotesApiService
import com.app.activitiesapplication.data.remote.quotes.toQuote
import com.app.activitiesapplication.domain.model.quotes.Quotes
import com.app.activitiesapplication.domain.repository.quotes.QuotesRepository

class QuotesRepositoryImpl(
    private val api: QuotesApiService
): QuotesRepository {
    override suspend fun getQuotes(): List<Quotes>  = api.getQuotes().map { it.toQuote() }
}