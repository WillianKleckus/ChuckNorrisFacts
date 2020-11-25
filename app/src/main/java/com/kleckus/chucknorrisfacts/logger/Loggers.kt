package com.kleckus.chucknorrisfacts.logger

import android.util.Log
import com.kleckus.domain.models.Constants.APP_TAG
import com.kleckus.domain.services.LogService

class DebugLogger : LogService {
    override fun log(message: String) {
        Log.i(APP_TAG, message)
    }

    override fun logError(message: String, error : Throwable) {
        Log.e(APP_TAG, message)
    }
}

class ProductionLogger : LogService{
    override fun log(message: String) {
    }

    override fun logError(message: String, error : Throwable) {
    }

}