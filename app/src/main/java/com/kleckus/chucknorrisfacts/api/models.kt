package com.kleckus.chucknorrisfacts.api

import com.google.gson.annotations.SerializedName

data class JokeResult(
    @SerializedName("icon_url") val iconUrl : String,
    val id : String,
    val url : String,
    val value : String
)

data class Categories (val categoryList: MutableList<String>)