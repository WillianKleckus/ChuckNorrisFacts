package com.kleckus.data.api.di

import com.kleckus.data.api.internal.cn.ChuckNorrisApi
import com.kleckus.domain.models.InjectionTags
import com.kleckus.domain.services.ApiService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider


object ApiServiceModule {
    operator fun invoke() = DI.Module(name = "api-service-module") {
        bind<ApiService>(tag = InjectionTags.CN_API) with provider {
            ChuckNorrisApi()
        }
    }
}