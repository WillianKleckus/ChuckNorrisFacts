package com.kleckus.ui.main

import androidx.lifecycle.ViewModel
import com.kleckus.domain.models.Constants.QUERY_MINIMUM_LENGTH
import com.kleckus.domain.models.Joke
import com.kleckus.domain.services.ApiService
import com.kleckus.ui.onIOThread
import com.kleckus.ui.onMainThread

class MainViewModel(private val service : ApiService) : ViewModel(){

    var onResult : (result: MutableList<Joke>) -> Unit = {}

    private fun getRandomJoke() {
        onIOThread {
            val result = service.getRandomJoke()
            val list = mutableListOf(result)
            onMainThread { onResult(list) }
        }
    }

    fun searchFor(text : String){
        if(text.isNullOrBlank() || text.length < QUERY_MINIMUM_LENGTH){
            getRandomJoke()
        }
        else onIOThread {
                val result = service.searchFor(text)
                onMainThread { onResult(result) }
            }
    }
}