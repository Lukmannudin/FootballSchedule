package com.lukmannudin.assosiate.footballclubschedule.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukmannudin.assosiate.footballclubschedule.Model.Players
import com.lukmannudin.assosiate.footballclubschedule.R
import kotlinx.android.synthetic.main.list_player.view.*

class PlayerAdapter(private val list:ArrayList<Players>) : RecyclerView.Adapter<PlayerAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_player,parent,false))
    }

    override fun getItemCount(): Int = list?.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lbList.text = list?.get(position)?.name

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}