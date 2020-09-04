package com.kleckus.chucknorrisfacts.system

import android.content.Intent
import com.kleckus.chucknorrisfacts.api.API_URL
import com.kleckus.chucknorrisfacts.api.Joke
import com.kleckus.chucknorrisfacts.ui.JokeUI

class Sharer{
    fun shareJoke(jokeUI : JokeUI){
        ChuckNorrisSystem.getLoadedJokes().forEach { joke ->
            if(joke.value == jokeUI.jokeStr){
                doShare(joke)
                return
            }
        }
        Util.log("Something went wrong")
    }

    private fun doShare(joke: Joke){
        if(ChuckNorrisSystem.currentContext == null){
            Util.log("Context is null, something went wrong")
            return
        }
        val message = "${joke.value}\n\nJoke url: ${joke.url}\nFind more at: $API_URL"

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        ChuckNorrisSystem.currentContext!!.startActivity(shareIntent)
    }
}