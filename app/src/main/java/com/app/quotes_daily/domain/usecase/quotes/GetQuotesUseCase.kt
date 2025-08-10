package com.app.quotes_daily.domain.usecase.quotes

import com.app.quotes_daily.domain.model.quotes.Quotes
import com.app.quotes_daily.domain.repository.quotes.QuotesRepository


class GetQuotesUseCase(private val repository: QuotesRepository) {
    suspend operator fun invoke(): List<Quotes> = repository.getQuotes()
}