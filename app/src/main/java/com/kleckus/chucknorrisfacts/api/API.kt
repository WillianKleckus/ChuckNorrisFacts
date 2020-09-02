package com.kleckus.chucknorrisfacts.api

import com.google.gson.GsonBuilder
import com.kleckus.chucknorrisfacts.system.ChuckNorrisSystem
import com.kleckus.chucknorrisfacts.system.Joke
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.chucknorris.io/jokes/"

class ChuckNorrisApi{
    var service : APIDef
    init {
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        service = retrofit.create<APIDef>(APIDef::class.java)
    }

    fun queryForJoke(query : String) : Observable<Joke>{
        ChuckNorrisSystem.clearLoadedJokeResults()
        return getListJokeResults(query)
            .flatMap { jokeResult ->
                ChuckNorrisSystem.addToLoadedJokeResults(jokeResult)
                Observable.just(Joke(jokeResult.value, jokeResult.categories))
        }
    }

    private fun getListJokeResults(query : String) : Observable<JokeResult>{
        return service.queryForJoke(query).flatMap { queryResult ->
            Observable.fromIterable(queryResult.result)
        }
    }

}