package com.kleckus.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kleckus.domain.models.Constants.NO_CATEGORY
import com.kleckus.domain.models.Joke
import com.kleckus.ui.R
import kotlinx.android.synthetic.main.joke_item_layout.view.*

class JokeAdapter(private val context : Context, val onSharing : (joke : Joke) -> Unit) : RecyclerView.Adapter<JokeAdapter.VH>() {

    private val dataSet = mutableListOf<Joke>()

    class VH(itemView : View) : RecyclerView.ViewHolder(itemView){}

    override fun getItemCount(): Int = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.joke_item_layout, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val joke = dataSet[position]
        val itemView = holder.itemView

        itemView.jokeTV.textSize = getTextDimen(joke.value.length)
        itemView.jokeTV.text = joke.value
        itemView.categoryChip.text = getCategoriesString(joke.categories)
        itemView.shareBt.setOnClickListener{ onSharing(joke) }
    }

    private fun getTextDimen(jokeLength : Int) : Float{
        return when{
            jokeLength > 80 -> { context.resources.getDimension(R.dimen.small_font_size) }
            jokeLength > 60 -> { context.resources.getDimension(R.dimen.medium_font_size) }
            else -> { context.resources.getDimension(R.dimen.big_font_size) }
        }
    }

    private fun getCategoriesString(list : MutableList<String>) : String{
        return if(list.isEmpty()) NO_CATEGORY
        else list.joinToString( separator = " - ").toUpperCase()
    }

    fun changeDataSet(jokeList : MutableList<Joke>){
        dataSet.clear()
        dataSet.addAll(jokeList)
        notifyDataSetChanged()
    }
}