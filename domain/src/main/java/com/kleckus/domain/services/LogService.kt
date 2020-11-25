package com.kleckus.domain.services

interface LogService {
    fun log(message : String)
    fun logError(message : String, error : Throwable)
}