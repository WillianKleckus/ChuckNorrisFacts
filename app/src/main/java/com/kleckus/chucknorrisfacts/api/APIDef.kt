package com.kleckus.chucknorrisfacts.api

import com.kleckus.chucknorrisfacts.ui.Joke
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
            list.add(JokeResult("iconUrl#1", "id#1", mutableListOf(), "url#1", "This is the #1 joke!"))
            list.add(JokeResult("iconUrl#2", "id#2", mutableListOf("Development"), "url#2", "This is the #2 joke!"))
            list.add(JokeResult("iconUrl#3", "id#3", mutableListOf("Economy"), "url#3", "This is the #3 joke!"))
            return list
        }

        fun queryForJoke(query : String) : Observable<QueryResult>{
            var jokeResultFound = mutableListOf<JokeResult>()
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