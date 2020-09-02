package com.kleckus.chucknorrisfacts.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kleckus.chucknorrisfacts.R
import com.kleckus.chucknorrisfacts.system.ChuckNorrisSystem
import com.kleckus.chucknorrisfacts.system.JokeAdapter
import com.kleckus.chucknorrisfacts.system.Util.Companion.log
import com.kleckus.chucknorrisfacts.system.Util.Companion.onFinnish
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var rvAdapter : JokeAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeLayout()
        initializeRV()

        svBar.onFinnish{text ->
            clearMessages()
            queryForJoke(text)
        }
    }

    private fun initializeLayout(){
        initialMessage.visibility = View.VISIBLE
        noResultsWindow.visibility = View.GONE
    }

    private fun initializeRV(){
        rvAdapter = JokeAdapter()
        rvJokeCard.adapter = rvAdapter
        rvJokeCard.layoutManager = LinearLayoutManager(this)
    }

    private fun clearMessages(){
        initialMessage.visibility = View.GONE
        noResultsWindow.visibility = View.GONE
    }

    private fun queryForJoke(text : String){
        ChuckNorrisSystem.queryForJoke(text){ jokeList ->
            if(jokeList.isEmpty()) showNoResultsMessage()
            rvAdapter.changeDataSet(jokeList)
        }
    }

    private fun showNoResultsMessage() {
        rvAdapter.changeDataSet(mutableListOf())
        noResultsWindow.visibility = View.VISIBLE
    }
}