package com.kleckus.chucknorrisfacts.api

import com.kleckus.chucknorrisfacts.system.Environment
import com.kleckus.chucknorrisfacts.ui.Joke
import io.reactivex.Observable

interface ChuckNorrisApi{
    fun queryForJoke(query : String) : Observable<Joke>
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