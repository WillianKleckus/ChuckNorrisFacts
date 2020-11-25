package com.kleckus.ui.main

import androidx.lifecycle.ViewModel
import cafe.adriel.dalek.Dalek
import cafe.adriel.dalek.DalekEvent
import com.kleckus.domain.models.Constants.QUERY_MINIMUM_LENGTH
import com.kleckus.domain.models.Joke
import com.kleckus.domain.services.ApiService
import com.kleckus.ui.onIOThread
import com.kleckus.ui.onMainThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val service : ApiService) : ViewModel(){
    fun getRandomJoke() : Flow<DalekEvent<List<Joke>>> =
        Dalek(Dispatchers.IO){
            listOf(service.getRandomJoke())
        }

    fun searchFor(text : String) : Flow<DalekEvent<List<Joke>>> =
        Dalek(Dispatchers.IO){
            service.searchFor(text)
        }
}