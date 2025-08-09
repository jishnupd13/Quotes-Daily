package com.app.activitiesapplication.data.remote.quotes

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface QuotesApiService {
    suspend fun getQuotes(): List<QuotesDto>
}

class QuotesApiServiceImpl(private val client: HttpClient):QuotesApiService{
    override suspend fun getQuotes(): List<QuotesDto> {
        return client.get("/personal_blog/quotes.json").body()
    }
}