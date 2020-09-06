package com.kleckus.chucknorrisfacts.api

import com.kleckus.chucknorrisfacts.system.Environment
import com.kleckus.chucknorrisfacts.ui.JokeUI
import io.reactivex.Observable

// Chose the use of Factoring Pattern, so it's easier to test the usage of the APIs
interface ChuckNorrisApi{
    fun queryForJoke(query : String) : Observable<JokeUI>
}

class FactoryCNApi{
    companion object{
        fun createApi(environment : Environment) : ChuckNorrisApi {
            return when(environment){
                Environment.TESTING -> MockedChuckNorrisApi()
                else -> RealChuckNorrisApi()
            }
        }
    }
}