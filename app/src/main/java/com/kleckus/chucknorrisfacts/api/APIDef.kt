package com.kleckus.chucknorrisfacts.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

private const val RANDOM_ENDPOINT = "random"
private const val CATEGORIES_ENDPOINT = "categories"
private const val RANDOM_FROM_CATEGORY_ENDPOINT ="random"
private const val QUERY_ENDPOINT = "search"

interface APIDef {
    @GET(RANDOM_ENDPOINT)
    fun getRandomJoke() : Observable<JokeResult>

    @GET(RANDOM_FROM_CATEGORY_ENDPOINT)
    fun getRandomJokeFrom(@Query("category") category : String) : Observable<JokeResult>

    @GET(CATEGORIES_ENDPOINT)
    fun getAvailableCategories() : Observable<MutableList<String>>

    @GET(QUERY_ENDPOINT)
    fun queryForJoke(@Query("query") query : String) : Observable<QueryResult>
}