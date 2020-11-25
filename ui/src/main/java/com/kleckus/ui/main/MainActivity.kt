package com.kleckus.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ShareCompat
import androidx.core.view.isInvisible
import cafe.adriel.dalek.*
import com.kleckus.domain.models.Constants.CN_API_URL
import com.kleckus.domain.models.Joke
import com.kleckus.domain.services.LogService
import com.kleckus.ui.R
import com.kleckus.ui.adapters.JokeAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
        setupViews()
    }

    private fun setupViews(){
        // Adapter
        recyclerView.adapter = adapter

        // SearchView
        searchView.setStartIconOnClickListener {
            val text = searchView.editText?.text.toString()
            queryForJoke(text)
        }
    }

    private fun queryForJoke(text : String, scope : CoroutineScope = CoroutineScope(Dispatchers.Main)){
        logger.log("Searching for -> $text")
        viewModel.searchFor(text).collectIn(scope){ event ->
            when(event){
                is Start -> toggleLoading(true)
                is Success -> handleSuccess(event.value)
                is Failure -> handleError(event.exception)
                is Finish -> toggleLoading(false)
            }
        }
    }

    private fun getRandomJoke(scope : CoroutineScope = CoroutineScope(Dispatchers.Main)){
        logger.log("Getting random joke")
        viewModel.getRandomJoke().collectIn(scope){ event ->
            when(event){
                is Start -> toggleLoading(true)
                is Success -> handleSuccess(event.value)
                is Failure -> handleError(event.exception)
                is Finish -> toggleLoading(false)
            }
        }
    }

    private fun handleSuccess(jokeList : List<Joke>){
        if(jokeList.isEmpty()) {
            logger.log("No joke found")
            getRandomJoke()
        }
        else{
            logger.log("Success loading jokes")
            adapter.changeDataSet(jokeList)
        }
    }

    private fun handleError(e : Throwable){
        logger.logError(e.message.toString(), e)
    }

    private fun onSharing(joke : Joke){
        logger.log("Sharing -> ${joke.value}")

        val message = "${joke.value}\n\nJoke url: ${joke.url}\nFind more at: $CN_API_URL"
        val shareIntent = ShareCompat.IntentBuilder
            .from(this)
            .setType("text/plain")
            .setText(message)
            .setChooserTitle("Share joke")
            .createChooserIntent()
        this.startActivity(shareIntent)
    }

    private fun toggleLoading(isLoading : Boolean){
        searchView.clearFocus()
        searchView.isEnabled = !isLoading
        loadingBar.isInvisible = !isLoading
    }
}