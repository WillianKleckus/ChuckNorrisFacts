package com.kleckus.chucknorrisfacts.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kleckus.chucknorrisfacts.R
import com.kleckus.chucknorrisfacts.system.ChuckNorrisSystem
import com.kleckus.chucknorrisfacts.system.JokeAdapter
import com.kleckus.chucknorrisfacts.system.Util.Companion.onFinnish
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var rvAdapter : JokeAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvAdapter = JokeAdapter()
        rvJokeCard.adapter = rvAdapter
        rvJokeCard.layoutManager = LinearLayoutManager(this)

        refreshButton.setOnClickListener { getRandomJoke() }
        svBar.onFinnish{text ->
            ChuckNorrisSystem.apiCalls.queryForJoke(text){ jokeList ->
                rvAdapter.changeDataSet(jokeList)
            }
        }
    }
    
    private fun getRandomJoke(){
        ChuckNorrisSystem.apiCalls.getRandomJoke{ joke ->
            rvAdapter.changeDataSet(mutableListOf(joke))
        }
    }
}