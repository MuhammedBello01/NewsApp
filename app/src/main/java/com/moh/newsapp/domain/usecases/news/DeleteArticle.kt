package com.moh.newsapp.domain.usecases.news

import com.moh.newsapp.domain.model.Article
import com.moh.newsapp.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository,
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}