package com.kleckus.chucknorrisfacts.logger

import com.kleckus.chucknorrisfacts.environment.Environment
import com.kleckus.domain.services.LogService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

object LoggerModule{
    operator fun invoke() = DI.Module(name = "logger-service"){
        bind<LogService>() with provider {
            when(instance<Environment>()){
                Environment.PRODUCTION -> ProductionLogger()
                Environment.DEBUG -> DebugLogger()
            }
        }
    }
}