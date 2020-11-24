package com.kleckus.chucknorrisfacts.app

import com.kleckus.chucknorrisfacts.BuildConfig
import com.kleckus.chucknorrisfacts.environment.Environment
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

object EnvironmentModule {
    operator fun invoke() = DI.Module(name = "environment-module"){
        bind<Environment>() with singleton {
            if(BuildConfig.DEBUG) Environment.DEBUG
            else Environment.PRODUCTION
        }
    }
}