package com.app.activitiesapplication.domain.usecase.quotes

import com.app.activitiesapplication.domain.model.quotes.Quotes
import com.app.activitiesapplication.domain.repository.quotes.QuotesRepository


class GetQuotesUseCase(private val repository: QuotesRepository) {
    suspend operator fun invoke(): List<Quotes> = repository.getQuotes()
}