package com.kleckus.data.api.internal

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

internal object RetrofitBuilder{
    private val contentType = MediaType.get("application/json")

    fun buildRetrofit(baseUrl : String) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory(contentType))
            .build()
    }

    inline fun <reified T> Retrofit.getRetrofitService() : T {
        return this.create<T>(T::class.java)
    }
}