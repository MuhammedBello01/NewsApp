package com.moh.newsapp.di

import android.app.Application
import androidx.room.Room
import com.moh.newsapp.data.manager.LocalUserImpl
import com.moh.newsapp.domain.repository.NewsRepository
import com.moh.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.moh.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.moh.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.moh.newsapp.data.local.NewsDao
import com.moh.newsapp.data.local.NewsDatabase
import com.moh.newsapp.data.local.NewsTypeConvertor
import com.moh.newsapp.domain.manager.LocalUserManager
import com.moh.newsapp.domain.usecases.news.DeleteArticle
import com.moh.newsapp.domain.usecases.news.GetNews
import com.moh.newsapp.domain.usecases.news.NewsUseCases
import com.moh.newsapp.domain.usecases.news.SearchNews
import com.moh.newsapp.domain.usecases.news.SelectArticle
import com.moh.newsapp.domain.usecases.news.SelectArticles
import com.moh.newsapp.domain.usecases.news.UpsertArticle
import com.moh.newsapp.data.remote.NewsApi
import com.moh.newsapp.data.repository.NewsRepositoryImpl
import com.moh.newsapp.utils.Constants.BASE_URL
import com.moh.newsapp.utils.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // specify the lifecycle. (SingletonComponent::class) means will as long as the application is alive
object AppModule {
    /*
    inside the module we define the dependencies we want to provide
     */

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserImpl(application)

    // also we need to provide the two use cases


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // this converts the json response to kotlin class
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)


    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration() // if you update something room will migrate it for you
            .build()
    }


    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao


}