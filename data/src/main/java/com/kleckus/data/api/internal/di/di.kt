package com.kleckus.data.api.internal.di

import com.kleckus.data.api.internal.RetrofitBuilder
import com.kleckus.data.api.internal.RetrofitBuilder.getRetrofitService
import com.kleckus.data.api.internal.cn.CNApiDefinition
import com.kleckus.domain.models.Constants.CN_API_BASE_URL
import org.kodein.di.*
import retrofit2.Retrofit

internal object RetrofitModule{
    operator fun invoke() = DI.Module(name = "retrofit-module"){
        bind<Retrofit>() with provider {
            RetrofitBuilder.buildRetrofit(CN_API_BASE_URL)
        }

        bind<CNApiDefinition>() with singleton {
            instance<Retrofit>().getRetrofitService<CNApiDefinition>()
        }
    }
}