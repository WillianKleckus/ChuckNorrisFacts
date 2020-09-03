package com.kleckus.chucknorrisfacts.api

import com.kleckus.chucknorrisfacts.system.ChuckNorrisSystem
import com.kleckus.chucknorrisfacts.ui.Joke
import io.reactivex.Observable

class MockedChuckNorrisApi : ChuckNorrisApi {
    override fun queryForJoke(query: String): Observable<Joke> {
        ChuckNorrisSystem.clearLoadedJokeResults()
        return getJokeResults(query).flatMap { jokeResult ->
            ChuckNorrisSystem.addToLoadedJokeResults(jokeResult)
            Observable.just(Joke(jokeResult.value, jokeResult.categories))
        }
    }

    private fun getJokeResults(query : String) : Observable<JokeResult> {
        return MockedApiDef.queryForJoke(query).flatMap {queryResult ->
            Observable.fromIterable(queryResult.result)
        }
    }
}