package com.kleckus.chucknorrisfacts.system

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.appcompat.widget.SearchView
import com.kleckus.chucknorrisfacts.api.ChuckNorrisApi
import com.kleckus.chucknorrisfacts.api.JokeResult
import com.kleckus.chucknorrisfacts.system.Util.Companion.log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChuckNorrisSystem : Application(){
    companion object{
        private val api = ChuckNorrisApi()
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
    }
}

// Mostly stuff to reduce boilerplate code
class Util{
    companion object{
        fun log(string : String){
            Log.i("CN", string)
        }

        fun SearchView.onFinnish(doSomething : (text : String) -> Unit){
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
                override fun onQueryTextSubmit(query: String): Boolean {
                    doSomething(query)
                    return false
                }
            })
        }
    }
}