package com.kleckus.chucknorrisfacts.wip

import android.app.Application
import com.kleckus.data.api.di.ApiServiceModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class App : Application(), DIAware{
    override val di = DI.lazy{
        import(ApiServiceModule())
    }
}