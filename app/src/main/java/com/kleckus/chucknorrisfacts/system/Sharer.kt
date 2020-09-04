package com.kleckus.chucknorrisfacts.system

import android.content.Intent
import com.kleckus.chucknorrisfacts.api.API_URL
import com.kleckus.chucknorrisfacts.api.JokeResult
import com.kleckus.chucknorrisfacts.ui.Joke

class Sharer{
    fun shareJoke(joke : Joke){
        ChuckNorrisSystem.getLoadedJokes().forEach { jokeResult ->
            if(jokeResult.value == joke.jokeStr){
                doShare(jokeResult)
                return
            }
        }
        Util.log("Something went wrong")
    }

    private fun doShare(jokeResult: JokeResult){
        if(ChuckNorrisSystem.currentContext == null){
            Util.log("Context is null, something went wrong")
            return
        }
        val message = "${jokeResult.value}\n\nJoke url: ${jokeResult.url}\nFind more at: $API_URL"

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        ChuckNorrisSystem.currentContext!!.startActivity(shareIntent)
    }
}