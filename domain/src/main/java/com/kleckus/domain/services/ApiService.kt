package com.kleckus.domain.services

import com.kleckus.domain.models.Joke

interface ApiService {
    suspend fun getRandomJoke() : Joke
    suspend fun searchFor(text : String) : MutableList<Joke>
}