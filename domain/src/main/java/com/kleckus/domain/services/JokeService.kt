package com.kleckus.domain.services

import com.kleckus.domain.models.Joke

interface JokeService {
    suspend fun getRandomJoke() : Joke
    suspend fun searchFor(text : String) : List<Joke>
}