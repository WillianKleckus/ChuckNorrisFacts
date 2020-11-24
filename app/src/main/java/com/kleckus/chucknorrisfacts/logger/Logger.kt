package com.kleckus.chucknorrisfacts.logger

import android.util.Log
import com.kleckus.chucknorrisfacts.App
import com.kleckus.chucknorrisfacts.environment.Environment
import com.kleckus.domain.models.Constants.APP_TAG
import com.kleckus.domain.services.LogService

class Logger : LogService {
    override fun log(message: String) {
        when(App.environment){
            Environment.PRODUCTION ->{
                // TODO - use analytics
                Log.i("$APP_TAG - PRODUCTION", message)
            }
            Environment.DEBUG -> Log.i(APP_TAG, message)
        }
    }

    override fun logError(message: String) {
        when(App.environment){
            Environment.PRODUCTION ->{
                // TODO - use analytics
                Log.e("$APP_TAG - PRODUCTION", message)
            }
            Environment.DEBUG -> Log.e(APP_TAG, message)
        }
    }
}