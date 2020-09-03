package com.kleckus.chucknorrisfacts.api

import com.google.gson.GsonBuilder
import com.kleckus.chucknorrisfacts.system.ChuckNorrisSystem
import com.kleckus.chucknorrisfacts.ui.Joke
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.chucknorris.io/jokes/"
const val API_URL = "api.chucknorris.io"

class RealChuckNorrisApi : ChuckNorrisApi{
    private val service : APIDef = establishService()

    private fun establishService() : APIDef{
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create<APIDef>(APIDef::class.java)
    }

    private fun getJokeResults(query : String) : Observable<JokeResult> {
        return service.queryForJoke(query).flatMap { queryResult ->
            Observable.fromIterable(queryResult.result)
        }
    }

    override fun queryForJoke(query : String) : Observable<Joke> {
        ChuckNorrisSystem.clearLoadedJokeResults()
        return getJokeResults(query)
            .flatMap { jokeResult ->
                ChuckNorrisSystem.addToLoadedJokeResults(jokeResult)
                Observable.just(Joke(jokeResult.value, jokeResult.categories))
            }
    }
}