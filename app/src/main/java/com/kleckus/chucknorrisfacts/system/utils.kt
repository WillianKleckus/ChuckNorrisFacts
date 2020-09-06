package com.kleckus.chucknorrisfacts.system

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.appcompat.widget.SearchView

// Mostly stuff to reduce boilerplate code
class Util{
    companion object{
        fun log(string : String){
            Log.i("CN", string)
        }

        // Reduces boilerplate code when adding a "onFinnish" listener to a SearchView
        fun SearchView.onFinnish(doSomething : (text : String) -> Unit){
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
                override fun onQueryTextSubmit(query: String): Boolean {
                    doSomething(query)
                    return false
                }
            })
        }

        // Checks if internet connection is available
        fun hasInternetConnection() : Boolean{
            if(ChuckNorrisSystem.currentContext == null) return false

            val connectivityManager =
                ChuckNorrisSystem.currentContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            if(capabilities != null){
                when{
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->{
                        log("Has internet -> NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        log("Has internet -> NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        log("Has internet -> NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                    else -> {
                        log("Has no internet connection")
                        return false
                    }
                }
            }
            else {
                log("Has no internet connection")
                return false
            }
        }
    }
}