package com.kleckus.data.api.di

import com.kleckus.data.api.internal.cn.ChuckNorrisApi
import com.kleckus.data.api.internal.di.RetrofitModule
import com.kleckus.domain.models.InjectionTags
import com.kleckus.domain.services.ApiService
import org.kodein.di.*


object ApiServiceModule {
    operator fun invoke() = DI.Module(name = "api-service-module") {
        import(RetrofitModule())

        bind<ApiService>() with singleton {
            ChuckNorrisApi(instance())
        }
    }
}