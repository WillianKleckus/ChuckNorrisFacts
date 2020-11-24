package com.kleckus.chucknorrisfacts.logger

import com.kleckus.domain.services.LogService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider

object LoggerModule{
    operator fun invoke() = DI.Module(name = "logger-service"){
        bind<LogService>() with provider {
            Logger()
        }
    }
}