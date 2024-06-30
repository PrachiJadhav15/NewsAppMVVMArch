package com.prachi.newsappmvvmarch.data.api

import com.prachi.newsappmvvmarch.data.model.NewsListByCountry
import com.prachi.newsappmvvmarch.data.model.NewsListBySource
import com.prachi.newsappmvvmarch.data.model.NewsSourcesResponse
import com.prachi.newsappmvvmarch.data.model.TopHeadlinesResponse
import com.prachi.newsappmvvmarch.utils.AppConstant.NEWS_APP_API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("X-Api-Key: $NEWS_APP_API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $NEWS_APP_API_KEY")
    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    @Headers("X-Api-Key: $NEWS_APP_API_KEY")
    @GET("top-headlines")
    suspend fun getNewsListBySource(@Query("sources") source: String): NewsListBySource

    @Headers("X-Api-Key: $NEWS_APP_API_KEY")
    @GET("top-headlines")
    suspend fun getNewsListByCountry(@Query("country") country: String): NewsListByCountry
}
