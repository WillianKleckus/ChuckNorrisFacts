package com.kleckus.data.api.internal.cn

import com.kleckus.data.api.internal.API
import com.kleckus.domain.models.Joke
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CNApiDefinition : API {
    companion object{
        private const val RANDOM_ENDPOINT = "random"
        private const val CATEGORIES_ENDPOINT = "categories"
        private const val RANDOM_FROM_CATEGORY_ENDPOINT ="random"
        private const val QUERY_ENDPOINT = "search"
    }
    @GET(RANDOM_ENDPOINT)
    suspend fun getRandomJoke() : Joke

    @GET(RANDOM_FROM_CATEGORY_ENDPOINT)
    suspend fun getRandomJokeFrom(@Query("category") category : String) : Joke

    @GET(CATEGORIES_ENDPOINT)
    suspend fun getAvailableCategories() : MutableList<String>

    @GET(QUERY_ENDPOINT)
    suspend fun queryForJoke(@Query("query") query : String) : QueryResult
}