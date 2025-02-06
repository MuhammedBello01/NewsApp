package com.moh.newsapp.presentation.bookmark

import com.moh.newsapp.domain.model.Article

data class BookMarkState(
    val articles: List<Article> = emptyList()
)