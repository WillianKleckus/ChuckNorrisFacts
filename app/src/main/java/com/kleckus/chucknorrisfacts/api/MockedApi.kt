package com.kleckus.chucknorrisfacts.api

import com.kleckus.chucknorrisfacts.system.ChuckNorrisSystem
import com.kleckus.chucknorrisfacts.ui.JokeUI
import io.reactivex.Observable

// Self explanatory, fake API usage
class MockedChuckNorrisApi : ChuckNorrisApi {
    override fun queryForJoke(query: String): Observable<JokeUI> {
        ChuckNorrisSystem.clearLoadedJokes()
        return getJoke(query).flatMap { joke ->
            ChuckNorrisSystem.addToLoadedJokes(joke)
            Observable.just(JokeUI(joke.value, joke.categories))
        }
    }

    private fun getJoke(query : String) : Observable<Joke> {
        return MockedApiDef.queryForJoke(query).flatMap {queryResult ->
            Observable.fromIterable(queryResult.result)
        }
    }
}