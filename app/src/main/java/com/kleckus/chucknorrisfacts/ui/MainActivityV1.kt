package com.kleckus.chucknorrisfacts.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kleckus.chucknorrisfacts.R
import com.kleckus.domain.models.InjectionTags
import com.kleckus.domain.services.ApiService
import kotlinx.android.synthetic.main.activity_main_v1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class MainActivityV1 : AppCompatActivity(), DIAware{

    override val di : DI by closestDI(this)

    private val service : ApiService by instance(tag = InjectionTags.Data.CN_API)

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_v1)

        CoroutineScope(Dispatchers.IO).launch{
            val joke = service.getRandomJoke()
            Log.i("CN Log","Caught joke: ${joke.value}")
        }

    }
}