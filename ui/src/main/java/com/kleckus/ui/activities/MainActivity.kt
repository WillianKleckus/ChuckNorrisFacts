package com.kleckus.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.kleckus.domain.log
import com.kleckus.domain.models.Constants.API_URL
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
            toggleLoading(true)
            val text = searchView.editText?.text.toString()
            viewModel.searchFor(text)
        }
    }

    private fun startRecyclerView(){
        recyclerView.adapter = adapter
    }

    private fun onResult(jokeList : MutableList<Joke>){
        log(jokeList.toString())
        adapter.changeDataSet(jokeList)
        toggleLoading(false)
    }

    private fun onSharing(joke : Joke){
        log("Sharing joke -> ${joke.value}")

        val message = "${joke.value}\n\nJoke url: ${joke.url}\nFind more at: $API_URL"
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        this.startActivity(shareIntent)
    }

    private fun toggleLoading(isLoading : Boolean){
        loadingBar.isVisible = isLoading
        searchView.clearFocus()
        searchInput.isFocusableInTouchMode = !isLoading
        searchInput.isFocusable = !isLoading
    }
}