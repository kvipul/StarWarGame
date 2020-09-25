package com.sablania.starwargame.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sablania.starwargame.databinding.ItemPlayersBinding
import com.sablania.starwargame.pojos.PlayerDetail

class PlayersAdapter(val itemClick: (PlayerDetail) -> Unit) : RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    private val list = ArrayList<PlayerDetail>()

    fun setData(newList: ArrayList<PlayerDetail>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        return PlayersViewHolder(
            ItemPlayersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PlayersViewHolder(val view: ItemPlayersBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: PlayerDetail) {
            view.apply {
                tvName.text = item.player.name
                tvPoints.text = item.score.toString()
                Glide.with(root.context).load(item.player.icon).into(ivThumbnail)
                container.setOnClickListener {
                    itemClick(item)
                }
            }
        }
    }
}
