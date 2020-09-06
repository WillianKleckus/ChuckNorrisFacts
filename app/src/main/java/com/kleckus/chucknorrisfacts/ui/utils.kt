package com.kleckus.chucknorrisfacts.ui

import com.kleckus.chucknorrisfacts.R
import com.kleckus.chucknorrisfacts.system.ChuckNorrisSystem

class Util{
    companion object{
        // Transform a list of strings to a single string.
        fun listToString(categoryList : MutableList<String>) : String{
            if(categoryList.isEmpty()) return ChuckNorrisSystem.currentContext!!.getString(R.string.joke_no_category)
            var ret = ""
            categoryList.forEach { cat ->
                ret += "  ${cat.toUpperCase()}"
            }
            return ret.trim()
        }

        // Gets the joke text size depending on the amount of characters it has
        fun getJokeTextSize(jokeUI: JokeUI) : Float{
            val jokeLength = jokeUI.jokeStr.length
            return when{
                jokeLength > 80 -> { getDimen(R.dimen.small_font_size) }
                jokeLength > 60 -> { getDimen(R.dimen.medium_font_size) }
                else -> { getDimen(R.dimen.big_font_size) }
            }
        }

        private fun getDimen(id : Int) : Float{
            return ChuckNorrisSystem.currentContext!!.resources.getDimension(id)
        }
    }
}