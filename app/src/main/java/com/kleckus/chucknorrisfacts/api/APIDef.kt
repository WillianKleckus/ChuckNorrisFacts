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

class MockedApi{
    companion object{
        private val database = setupDatabase()
        private fun setupDatabase() : MutableList<JokeResult> {
            val list = mutableListOf<JokeResult>()
            list.add(JokeResult("iconUrl#1", "id#1", mutableListOf(), "url#1", "This is the joke #1!"))
            list.add(JokeResult("iconUrl#2", "id#2", mutableListOf("Development"), "url#2", "This is the joke #2!"))
            list.add(JokeResult("iconUrl#3", "id#3", mutableListOf("Economy"), "url#3", "This is the joke #3!"))
            // Testing for less than 60 characters
            list.add(JokeResult("iconUrl#4", "id#4", mutableListOf("Development", "Economy"), "url#4", "This is the joke #4, but has more characters than #1"))
            // Testing for more than 60 but less than 80 characters
            list.add(JokeResult("iconUrl#5", "id#5", mutableListOf(), "url#5", "This is the joke #5, but this one has a some more characters than joke #2"))
            // Testing for more than 80 characters
            list.add(JokeResult("iconUrl#6", "id#6", mutableListOf(), "url#6", "This is the joke #6, but this one... Oh man, this one has A LOT more characters than #3, but this is happening mainly for testing the font sizing."))
            return list
        }

        fun queryForJoke(query : String) : Observable<QueryResult>{
            val jokeResultFound = mutableListOf<JokeResult>()
            var total = 0
            database.forEach {jokeResult ->
                if(jokeResult.value.contains(query)) {
                    jokeResultFound.add(jokeResult)
                    total++
                }
            }
            return Observable.just(QueryResult(total, jokeResultFound))
        }
    }
}