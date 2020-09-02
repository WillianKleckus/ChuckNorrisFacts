package com.kleckus.chucknorrisfacts.system

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.widget.SearchView
import com.kleckus.chucknorrisfacts.api.API_URL
import com.kleckus.chucknorrisfacts.api.ChuckNorrisApi
import com.kleckus.chucknorrisfacts.api.JokeResult
import com.kleckus.chucknorrisfacts.system.Util.Companion.log
import com.kleckus.chucknorrisfacts.ui.Joke
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChuckNorrisSystem : Application(){
    companion object{
        var currentContext : Context? = null
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

        fun shareJoke(joke : Joke){
            loadedJokeResults.forEach { jokeResult ->
                if(jokeResult.value == joke.jokeStr){
                    doShare(jokeResult)
                }
                else log("Something went wrong")
            }
        }

        private fun doShare(jokeResult: JokeResult){
            if(currentContext == null){
                log("Context is null, something went wrong")
                return
            }
            val message = "${jokeResult.value}\n\nJoke url: ${jokeResult.url}\nFind more at: $API_URL"

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            currentContext!!.startActivity(shareIntent)
        }
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