package com.kleckus.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kleckus.domain.models.Joke
import com.kleckus.ui.R
import com.kleckus.ui.adapters.JokeAdapter
import com.kleckus.ui.vm.ApiViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy{
        val vm = ApiViewModel(this)
        vm.onResult = ::onResult
        vm
    }
    private val adapter by lazy { JokeAdapter(this,::onSharing) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTextListener()
        startRecyclerView()
    }

    private fun setTextListener(){
        searchView.setStartIconOnClickListener {
            val text = searchView.editText?.text.toString()
            viewModel.searchFor(text)
        }
    }

    private fun startRecyclerView(){
        recyclerView.adapter = adapter
    }

    private fun onResult(jokeList : MutableList<Joke>){
        Log.i("CN Log", jokeList.toString())
        adapter.changeDataSet(jokeList)
    }

    private fun onSharing(joke : Joke){
        Log.i("CN Log", "Sharing joke -> ${joke.value}")
    }
}