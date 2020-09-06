package com.kleckus.chucknorrisfacts.system

import android.widget.Toast
import com.kleckus.chucknorrisfacts.system.Util.Companion.hasInternetConnection
import com.kleckus.chucknorrisfacts.system.Util.Companion.log

// This is the error handler:
// When something bad happens when trying to retrieve data from servers, the app will toast a
// message to the user with the following info, depending on what has happened.
class ErrorHandler{
    companion object{
        fun handleError(code : String){
            if(hasInternetConnection()){
                when(code.trim()){
                    ErrorCode.SERVER_ERROR.code -> showErrorMessage(ErrorCode.SERVER_ERROR.message)
                    ErrorCode.CLIENT_ERROR.code -> showErrorMessage(ErrorCode.CLIENT_ERROR.message)
                    ErrorCode.CLIENT_TIMEOUT.code -> showErrorMessage(ErrorCode.CLIENT_TIMEOUT.message)
                    else -> {
                        showErrorMessage(ErrorCode.GENERIC_ERROR.message)
                        log(code)
                    }
                }
            } else showErrorMessage(ErrorCode.NO_INTERNET.message)
        }

        private fun showErrorMessage(message : String){
            Toast.makeText(ChuckNorrisSystem.currentContext, message, Toast.LENGTH_LONG).show()
        }
    }
}

enum class ErrorCode(val code : String, val message: String){
    SERVER_ERROR("HTTP 500", "There was an internal server error."),
    CLIENT_ERROR("HTTP 400", "Bad request. Please, try something else."),
    CLIENT_TIMEOUT("HTTP 408", "Connection timed out. Please, check your connection and try again."),
    GENERIC_ERROR("GENERIC_ERROR", "Sorry, something occurred. Try again."),
    NO_INTERNET("NO_INTERNET", "Please, check your internet connection.")
}