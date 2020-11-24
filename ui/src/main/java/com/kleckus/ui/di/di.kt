package com.kleckus.ui.di

import com.kleckus.ui.main.MainViewModel
import org.kodein.di.*
import org.kodein.di.android.x.AndroidLifecycleScope

object ViewModelsModule {
    operator fun invoke() = DI.Module(name = "view-models-module"){
        //MainViewModel
        bind<MainViewModel>() with scoped(AndroidLifecycleScope.multiItem).singleton{
            MainViewModel(
                service = instance()
            )
        }
    }
}