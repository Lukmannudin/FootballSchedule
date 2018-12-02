package com.lukmannudin.assosiate.footballclubschedule.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukmannudin.assosiate.footballclubschedule.Model.Players
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_player.view.*

class PlayerAdapter(private val players: List<Players>, private val listener: (Players) -> Unit) : RecyclerView.Adapter<PlayerAdapter.PlayerHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        return PlayerHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_player,parent,false))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindItem(players[position],listener)
    }

    class PlayerHolder(view: View) : RecyclerView.ViewHolder(view){
        private val playerBadge = view.imgListPlayer
        private val playerName = view.tvPlayerName
        private val playerPosition = view.tvPlayerPosition
        fun bindItem(players: Players,listener:(Players)->Unit){
            Picasso.get().load(players.strThumb).into(playerBadge)
            playerName.text = players.strPlayer
            playerPosition.text = players.strPosition
            itemView.setOnClickListener { listener(players) }
        }
    }

}