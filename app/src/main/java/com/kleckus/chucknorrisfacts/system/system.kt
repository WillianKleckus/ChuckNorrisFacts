package com.kleckus.chucknorrisfacts.system

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import com.kleckus.chucknorrisfacts.api.API_URL
import com.kleckus.chucknorrisfacts.api.FactoryCNApi
import com.kleckus.chucknorrisfacts.api.JokeResult
import com.kleckus.chucknorrisfacts.system.Util.Companion.log
import com.kleckus.chucknorrisfacts.ui.Joke
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChuckNorrisSystem : Application(){
    companion object{
        var currentContext : Context? = null
        var sharer = Sharer()
        private val api = FactoryCNApi.createApi(Environment.PRODUCTION)
        private val loadedJokeResults = mutableListOf<JokeResult>()

        @SuppressLint("CheckResult")
        fun queryForJoke(text : String, then : (jokeList : MutableList<Joke>) -> Unit){
            val jokeList = mutableListOf<Joke>()
            api.queryForJoke(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { joke -> jokeList.add(joke) },
                    { error -> log(error.message.toString()) },
                    { then(jokeList)}
                )
        }

        fun clearLoadedJokeResults(){ loadedJokeResults.clear() }
        fun addToLoadedJokeResults(jokeResult : JokeResult){ loadedJokeResults.add(jokeResult) }
        fun getLoadedJokes(): MutableList<JokeResult>{return loadedJokeResults}
    }
}