package com.kleckus.chucknorrisfacts.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kleckus.chucknorrisfacts.R
import com.kleckus.chucknorrisfacts.api.ChuckNorrisApi
import com.kleckus.chucknorrisfacts.system.Joke
import com.kleckus.chucknorrisfacts.system.JokeAdapter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var api : ChuckNorrisApi
    lateinit var rvAdapter : JokeAdapter
    private val randomJokes = mutableListOf<Joke>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvAdapter = JokeAdapter()
        rvJokeCard.adapter = rvAdapter
        rvJokeCard.layoutManager = LinearLayoutManager(this)

        refreshButton.setOnClickListener { refresh() }

        api = ChuckNorrisApi()
    }
    
    private fun refresh(){
        api.getRandomJoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    joke -> randomJokes.add(joke)
            },{
                    e -> e.printStackTrace()
            },{
                rvAdapter.changeDataSet(randomJokes)
            })
    }
}