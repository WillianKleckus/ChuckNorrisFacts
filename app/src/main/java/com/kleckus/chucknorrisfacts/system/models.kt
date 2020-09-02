package com.kleckus.chucknorrisfacts.system

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kleckus.chucknorrisfacts.R
import kotlinx.android.synthetic.main.joke_card.view.*

data class Joke(val jokeStr : String, private val jokeCategories : MutableList<String>){
    val categoriesStr : String
    init{
        categoriesStr = listToString()
    }
    private fun listToString() : String{
        if(jokeCategories.isEmpty()) return "UNCATEGORIZED"
        var ret = ""
        jokeCategories.forEach { cat ->
            ret += " ${cat.toUpperCase()}"
        }
        return ret.trim()
    }
}

class JokeAdapter : RecyclerView.Adapter<JokeAdapter.VH>(){
    private var dataSetList = mutableListOf<Joke>()
    var share : (joke : Joke) -> Unit = {}
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

        card.tvJokeValue.text = currentJoke.jokeStr
        card.tvJokeCategory.text = currentJoke.categoriesStr
        card.shareButton.setOnClickListener { share(currentJoke) }
    }

    fun changeDataSet(list : MutableList<Joke>){
        dataSetList = list
        notifyDataSetChanged()
    }

}