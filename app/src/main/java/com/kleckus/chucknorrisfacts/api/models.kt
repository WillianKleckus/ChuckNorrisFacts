package com.kleckus.chucknorrisfacts.api

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("icon_url") val iconUrl : String,
    val id : String,
    val categories : MutableList<String>,
    val url : String,
    val value : String
)

data class Categories (val categoryList: MutableList<String>)

data class QueryResult(val total : Int, val result : MutableList<Joke>)