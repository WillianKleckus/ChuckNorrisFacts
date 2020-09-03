package com.kleckus.chucknorrisfacts.system

import android.util.Log
import androidx.appcompat.widget.SearchView

// Mostly stuff to reduce boilerplate code
class Util{
    companion object{
        fun log(string : String){
            Log.i("CN", string)
        }

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
    }
}