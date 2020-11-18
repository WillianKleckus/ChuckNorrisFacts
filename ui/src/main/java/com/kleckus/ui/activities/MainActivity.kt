package com.kleckus.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.textfield.TextInputEditText
import com.kleckus.domain.models.Joke
import com.kleckus.ui.R
import com.kleckus.ui.toastMsg
import com.kleckus.ui.vm.ApiViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val viewModel = ApiViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.onResult = ::onResult

        searchView.setStartIconOnClickListener {
            val text = searchView.editText?.text.toString()
            viewModel.searchFor(text)
        }
    }

    private fun onResult(list : MutableList<Joke>){
        Log.i("CN Log", list.toString())
    }
}