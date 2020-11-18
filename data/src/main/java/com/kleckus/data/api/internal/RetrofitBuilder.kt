package com.kleckus.data.api.internal

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface API

internal object RetrofitBuilder{
    private val gson = GsonBuilder().setLenient().create()

    fun buildRetrofit(baseUrl : String) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getRetrofitService(retrofit: Retrofit) : API {
        return retrofit.create<API>(API::class.java)
    }
}