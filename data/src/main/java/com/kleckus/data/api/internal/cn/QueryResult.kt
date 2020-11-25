package com.kleckus.data.api.internal.cn

import com.kleckus.domain.models.Joke
import kotlinx.serialization.Serializable

@Serializable
internal data class QueryResult(
    val total : Int,
    val result : MutableList<Joke>
    )