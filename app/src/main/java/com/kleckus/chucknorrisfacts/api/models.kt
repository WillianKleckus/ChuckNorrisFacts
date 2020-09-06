package com.kleckus.chucknorrisfacts.api

import com.google.gson.annotations.SerializedName

// These are the classes to define the database info.
// Therefore has the complete info about the Jokes, unlike the UI class, that
// has only the necessary for the UI.
data class Joke(
    @SerializedName("icon_url") val iconUrl : String,
    val id : String,
    val categories : MutableList<String>,
    val url : String,
    val value : String
)

data class Categories (val categoryList: MutableList<String>)

data class QueryResult(val total : Int, val result : MutableList<Joke>)