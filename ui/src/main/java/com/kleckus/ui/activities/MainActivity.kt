package com.kleckus.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kleckus.domain.models.Joke
import com.kleckus.ui.R
import com.kleckus.ui.vm.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel = MainActivityViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.onResult = ::onResult

        searchEngine.setStartIconOnClickListener {

        }
    }

    private fun onResult(list : MutableList<Joke>){
        Log.i("CN Log", list.toString())
    }
}