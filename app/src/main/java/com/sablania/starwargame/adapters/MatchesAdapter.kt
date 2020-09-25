package com.sablania.starwargame.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sablania.starwargame.databinding.ItemMatchesBinding
import com.sablania.starwargame.pojos.Match

class MatchesAdapter(val itemClick: (Match) -> Unit) :
    RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder>() {

    private val list = ArrayList<Match>()

    fun setData(newList: ArrayList<Match>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        return MatchesViewHolder(
            ItemMatchesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class MatchesViewHolder(val view: ItemMatchesBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(item: Match) {
            view.apply {
                tvP1.text = item.player1.id.toString()
                tvP2.text = item.player2.id.toString()
                tvScore.text = "${item.player1.score} - ${item.player2.score}"
            }
        }
    }
}
