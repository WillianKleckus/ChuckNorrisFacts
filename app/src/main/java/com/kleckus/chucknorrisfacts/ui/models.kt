package com.kleckus.chucknorrisfacts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kleckus.chucknorrisfacts.R
import com.kleckus.chucknorrisfacts.ui.Util.Companion.getJokeTextSize
import com.kleckus.chucknorrisfacts.ui.Util.Companion.listToString
import kotlinx.android.synthetic.main.joke_card.view.*

// This is the UI model of the jokes and the RecView adapter.
data class JokeUI(val jokeStr : String, private val jokeCategories : MutableList<String>){
    val categoriesStr : String = listToString(jokeCategories)
}

class JokeAdapter : RecyclerView.Adapter<JokeAdapter.VH>(){
    private var dataSetList = mutableListOf<JokeUI>()
    var share : (jokeUI : JokeUI) -> Unit = {}
    class VH (itemView : View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.joke_card, parent,false)
        return VH(v)
    }

    override fun getItemCount(): Int = dataSetList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val currentJoke = dataSetList[position]
        val card = holder.itemView

        card.tvJokeValue.textSize = getJokeTextSize(currentJoke)
        card.tvJokeValue.text = currentJoke.jokeStr
        card.tvJokeCategory.text = currentJoke.categoriesStr
        card.shareButton.setOnClickListener { share(currentJoke) }
    }

    fun changeDataSet(list : MutableList<JokeUI>){
        dataSetList = list
        notifyDataSetChanged()
    }
}