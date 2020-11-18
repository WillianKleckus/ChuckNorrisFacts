package com.kleckus.chucknorrisfacts

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kleckus.chucknorrisfacts.system.ChuckNorrisSystem
import com.kleckus.chucknorrisfacts.ui.JokeUI
import com.kleckus.chucknorrisfacts.ui.MainActivityV1
import com.kleckus.chucknorrisfacts.ui.Util.Companion.getJokeTextSize

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class InstrumentedTesting {
    // This test checks if the function responsible for returning "UNCATEGORIZED" for the joke
    // with no category, is returning the expected.
    // This is an instrumented test because it needs access to the context to access the Resources.
    @Test
    fun test_checkUncategorizedReturn() {
        val scenario = ActivityScenario.launch(MainActivityV1::class.java)
        val expectedResult = ChuckNorrisSystem.currentContext!!.getString(R.string.joke_no_category)

        val emptyCategoryList = mutableListOf<String>()
        val dummyJoke = JokeUI("Dummy joke", emptyCategoryList)
        assertEquals(dummyJoke.categoriesStr, expectedResult)
    }


    // This test checks is something that has anything as category is NOT returning "UNCATEGORIZED"
    // as it should
    // This is an instrumented test because it needs access to the context to access the Resources.
    @Test
    fun test_checkCategorizedReturn(){
        val scenario = ActivityScenario.launch(MainActivityV1::class.java)
        val expectedResult = "ANY CATEGORY"

        val filledCategoryList = mutableListOf(expectedResult)
        val dummyJoke = JokeUI("Dummy joke", filledCategoryList)
        assertEquals(dummyJoke.categoriesStr, expectedResult)
    }


    // The following three tests will test if, depending on the size of the joke string, the correct
    // text size will be returned
    // This is an instrumented test because it needs access to the context to access the Resources.
    @Test
    fun test_checkTextSizeReturn(){
        val scenario = ActivityScenario.launch(MainActivityV1::class.java)
        val context = ChuckNorrisSystem.currentContext!!

        val jokeUISmallFont = JokeUI("A".repeat(81), mutableListOf())
        assertEquals(getJokeTextSize(jokeUISmallFont), context.resources.getDimension(R.dimen.small_font_size))

        val jokeUIMediumFont = JokeUI("B".repeat(70), mutableListOf())
        assertEquals(getJokeTextSize(jokeUIMediumFont), context.resources.getDimension(R.dimen.medium_font_size))

        val jokeUIBigFont = JokeUI("C".repeat(59), mutableListOf())
        assertEquals(getJokeTextSize(jokeUIBigFont), context.resources.getDimension(R.dimen.big_font_size))
    }
}