package com.kleckus.chucknorrisfacts

import com.kleckus.chucknorrisfacts.api.MockedApiDef
import com.kleckus.chucknorrisfacts.system.ChuckNorrisSystem
import com.kleckus.chucknorrisfacts.system.Environment
import org.junit.Test

import org.junit.Assert.*

class UnitTesting {
    // The string commonCharacter is in all of the jokes at the mocked database, then this
    // query should return all of the jokes inside the list
    @Test
    fun test_checkIfCanQueryCorrectly() {
        ChuckNorrisSystem.changeAppEnvironment(Environment.TESTING)
        val commonCharacter = "#"

        ChuckNorrisSystem.queryForJoke(commonCharacter) { jokeUIList ->
            assertEquals(jokeUIList.size, MockedApiDef.database.size)
        }
    }
}