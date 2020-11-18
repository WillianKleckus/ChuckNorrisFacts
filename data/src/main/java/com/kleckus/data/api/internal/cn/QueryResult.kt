package com.kleckus.data.api.internal.cn

import com.kleckus.domain.models.Joke

internal data class QueryResult(val total : Int, val result : MutableList<Joke>)