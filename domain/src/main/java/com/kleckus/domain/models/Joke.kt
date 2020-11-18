package com.kleckus.domain.models

data class Joke(
    val icon_url : String,
    val id : String,
    val categories : MutableList<String>,
    val url : String,
    val value : String
)