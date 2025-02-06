package com.moh.newsapp.domain.usecases.news

import com.moh.newsapp.domain.model.Article
import com.moh.newsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}