package com.kleckus.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kleckus.domain.models.Constants.API_URL
import com.kleckus.domain.models.Joke
import com.kleckus.domain.services.LogService
import com.kleckus.ui.R
import com.kleckus.ui.adapters.JokeAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {

    override val di : DI by di()

    private val viewModel : MainViewModel by instance()
    private val logger : LogService by instance()

    private val adapter by lazy { JokeAdapter(this,::onSharing) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupVm()
        setTextListener()
        startRecyclerView()
    }

    private fun setupVm(){
        viewModel.onResult = ::onResult
    }

    private fun setTextListener(){
        searchView.setStartIconOnClickListener {
            val text = searchView.editText?.text.toString()
            viewModel.searchFor(text)

            logger.log("Searching for -> $text")
        }
    }

    private fun startRecyclerView(){
        recyclerView.adapter = adapter
    }

    private fun onResult(jokeList : MutableList<Joke>){
        logger.log("Received -> $jokeList")

        adapter.changeDataSet(jokeList)
    }

    private fun onSharing(joke : Joke){
        logger.log("Sharing -> ${joke.value}")

        val message = "${joke.value}\n\nJoke url: ${joke.url}\nFind more at: $API_URL"
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        this.startActivity(shareIntent)
    }
}