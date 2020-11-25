package com.kleckus.data.api.internal.cn

import com.kleckus.domain.models.Joke
import com.kleckus.domain.services.ApiService

internal class ChuckNorrisApi(private val service : CNApiDefinition) : ApiService {
    override suspend fun getRandomJoke(): Joke {
        return service.getRandomJoke()
    }

    override suspend fun searchFor(text: String): List<Joke> {
        val result = service.queryForJoke(text)
        return result.result
    }
}