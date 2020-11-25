package com.kleckus.data.api.internal.cn

import com.kleckus.domain.models.Joke
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CNApiDefinition {
    private companion object{
        const val RANDOM_ENDPOINT = "random"
        const val CATEGORIES_ENDPOINT = "categories"
        const val RANDOM_FROM_CATEGORY_ENDPOINT ="random"
        const val QUERY_ENDPOINT = "search"
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