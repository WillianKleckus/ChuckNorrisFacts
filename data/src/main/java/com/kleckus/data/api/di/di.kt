package com.kleckus.data.api.di

import com.kleckus.data.api.internal.cn.ChuckNorrisApi
import com.kleckus.data.api.internal.di.RetrofitModule
import com.kleckus.domain.services.JokeService
import org.kodein.di.*


object ApiServiceModule {
    operator fun invoke() = DI.Module(name = "api-service-module") {
        import(RetrofitModule())

        bind<JokeService>() with singleton {
            ChuckNorrisApi(instance())
        }
    }
}