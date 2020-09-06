package com.kleckus.chucknorrisfacts.api

import com.google.gson.GsonBuilder
import com.kleckus.chucknorrisfacts.system.ChuckNorrisSystem
import com.kleckus.chucknorrisfacts.ui.JokeUI
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.chucknorris.io/jokes/"
const val API_URL = "api.chucknorris.io"

// The API methods that requires internet connection
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

    private fun getJoke(query : String) : Observable<Joke> {
        return service.queryForJoke(query).flatMap { queryResult ->
            Observable.fromIterable(queryResult.result)
        }
    }

    override fun queryForJoke(query : String) : Observable<JokeUI> {
        ChuckNorrisSystem.clearLoadedJokes()
        return getJoke(query)
            .flatMap { joke ->
                ChuckNorrisSystem.addToLoadedJokes(joke)
                Observable.just(JokeUI(joke.value, joke.categories))
            }
    }
}