package com.kleckus.data.api.internal.cn

import com.kleckus.data.api.internal.RetrofitBuilder
import com.kleckus.data.api.internal.RetrofitBuilder.getRetrofitService
import com.kleckus.domain.models.Joke
import com.kleckus.domain.services.ApiService

internal class ChuckNorrisApi() : ApiService {
    companion object{
        const val BASE_URL = "https://api.chucknorris.io/jokes/"
    }

    private val service : CNApiDefinition by lazy {
        RetrofitBuilder
            .buildRetrofit(BASE_URL)
            .getRetrofitService()
    }

    override suspend fun getRandomJoke(): Joke {
        return service.getRandomJoke()
    }

    override suspend fun searchFor(text: String): MutableList<Joke> {
        val result = service.queryForJoke(text)
        return result.result
    }
}