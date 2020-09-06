package com.kleckus.chucknorrisfacts.system

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.kleckus.chucknorrisfacts.api.FactoryCNApi
import com.kleckus.chucknorrisfacts.api.Joke
import com.kleckus.chucknorrisfacts.ui.JokeUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChuckNorrisSystem : Application(){
    companion object{
        var currentContext : Context? = null
        var sharer = Sharer()

        private var api = FactoryCNApi.createApi(Environment.PRODUCTION)
        private val loadedJoke = mutableListOf<Joke>()

        // This changes the Environment of the app, mainly for testing purposes
        fun changeAppEnvironment(environment : Environment){
            clearLoadedJokes()
            api = FactoryCNApi.createApi(environment)
        }

        // This function starts the search with the user's input keyword at the API database.
        // Receives in result a list of jokes already UI prepared.
        // Then expects the calling place to handle the result with the "then" function.
        @SuppressLint("CheckResult")
        fun queryForJoke(text : String, then : (jokeUIList : MutableList<JokeUI>) -> Unit){
            val jokeList = mutableListOf<JokeUI>()
            api.queryForJoke(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { joke -> jokeList.add(joke) },
                    { error ->
                        ErrorHandler.handleError(error.message.toString())
                        then(jokeList)
                    },
                    { then(jokeList) }
                )
        }

        fun clearLoadedJokes(){ loadedJoke.clear() }
        fun addToLoadedJokes(joke : Joke){ loadedJoke.add(joke) }
        fun getLoadedJokes(): MutableList<Joke>{return loadedJoke}
    }
}