package com.sablania.starwargame.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sablania.starwargame.R
import com.sablania.starwargame.databinding.ItemMatchesBinding
import com.sablania.starwargame.pojos.Match
import com.sablania.starwargame.pojos.MatchDetails

class MatchesAdapter(val playerId: Int, val itemClick: (MatchDetails) -> Unit) :
    RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder>() {

    private val list = ArrayList<MatchDetails>()

    fun setData(newList: ArrayList<MatchDetails>) {
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
        fun bind(item: MatchDetails) {
            view.apply {
                tvP1.text = item.player1.player.name
                tvP2.text = item.player2.player.name
                val p1Score = item.p1Score
                val p2Score = item.p2Score
                tvScore.text = "$p1Score - $p2Score"

                if(p1Score == p2Score) {
                    container.setBackgroundColor(
                        ContextCompat.getColor(
                            view.root.context,
                            R.color.white
                        )
                    )
                } else if ((p1Score > p2Score && item.player1.player.id == playerId) ||
                    (p1Score < p2Score && item.player2.player.id == playerId)) {
                    container.setBackgroundColor(
                        ContextCompat.getColor(
                            view.root.context,
                            R.color.green
                        )
                    )
                } else {
                    container.setBackgroundColor(
                        ContextCompat.getColor(
                            view.root.context,
                            R.color.red
                        )
                    )
                }
            }
        }
    }
}
