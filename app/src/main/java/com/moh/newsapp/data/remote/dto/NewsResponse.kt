package com.moh.newsapp.data.remote.dto

import com.moh.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)