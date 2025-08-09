package com.app.activitiesapplication.data.remote.quotes

import com.app.activitiesapplication.domain.model.quotes.Quotes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QuotesDto(
    @SerialName("id") val id: Int,
    @SerialName("quote") val quote: String,
    @SerialName("author") val author: String,
)

fun QuotesDto.toQuote() = Quotes(
    id = id,
    quote = quote,
    author = author
)
