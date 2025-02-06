package com.moh.newsapp.domain.usecases.news

import com.moh.newsapp.domain.model.Article
import com.moh.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>>{
        return newsRepository.selectArticles()
    }
}