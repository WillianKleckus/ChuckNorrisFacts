package com.kleckus.chucknorrisfacts.app

import android.app.Application
import com.kleckus.chucknorrisfacts.environment.Environment
import com.kleckus.chucknorrisfacts.logger.LoggerModule
import com.kleckus.data.api.di.ApiServiceModule
import com.kleckus.ui.di.ViewModelsModule
import com.kleckus.ui.main.MainViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware

class App : Application(), DIAware{
    override val di by DI.lazy{
        import(EnvironmentModule())
        import(ApiServiceModule())
        import(LoggerModule())
        import(ViewModelsModule())
    }
}