package com.kleckus.ui.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import com.kleckus.domain.models.InjectionTags
import com.kleckus.domain.models.Joke
import com.kleckus.domain.services.ApiService
import com.kleckus.ui.onIOThread
import com.kleckus.ui.onMainThread
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class MainActivityViewModel(
    context : Context
) : ViewModel(), DIAware {

    override val di : DI by closestDI(context)

    var onResult : (result: MutableList<Joke>) -> Unit = {}

    private val apiService : ApiService by instance(InjectionTags.Data.CN_API)

    fun getRandomJoke() {
        onIOThread {
            val result = apiService.getRandomJoke()
            val list = mutableListOf(result)
            onMainThread { onResult(list) }
        }
    }

    fun searchFor(text : String){
        onIOThread {
            val result = apiService.searchFor(text)
            onMainThread { onResult(result) }
        }
    }
}