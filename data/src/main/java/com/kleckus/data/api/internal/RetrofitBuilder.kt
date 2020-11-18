package com.kleckus.data.api.internal

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitBuilder{
    private val gson = GsonBuilder().setLenient().create()

    fun buildRetrofit(baseUrl : String) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    inline fun <reified T> Retrofit.getRetrofitService() : T {
        return this.create<T>(T::class.java)
    }
}