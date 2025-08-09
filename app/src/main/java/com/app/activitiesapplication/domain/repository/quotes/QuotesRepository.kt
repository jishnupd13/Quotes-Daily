package com.app.activitiesapplication.domain.repository.quotes

import com.app.activitiesapplication.domain.model.quotes.Quotes

interface QuotesRepository {
    suspend fun getQuotes(): List<Quotes>
}