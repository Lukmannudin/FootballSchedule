package com.lukmannudin.assosiate.footballclubschedule.Adapter

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lukmannudin.assosiate.footballclubschedule.FavoriteTeam
import com.lukmannudin.assosiate.footballclubschedule.Model.Teams
import com.lukmannudin.assosiate.footballclubschedule.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.favorite_team.view.*
import kotlinx.android.synthetic.main.team_view.view.*
import org.jetbrains.anko.*

class FavoritesTeamAdapter(private val favoriteTeam: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit) :
RecyclerView.Adapter<FavoritesTeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesTeamsViewHolder {

        return FavoritesTeamsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.favorite_team,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = favoriteTeam.size

    override fun onBindViewHolder(holder: FavoritesTeamsViewHolder, position: Int) {
        holder.bindItem(favoriteTeam[position], listener)
    }

}


class FavoritesTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamImage: ImageView = view.imgTeamFavorite
    private val teamName: TextView = view.tvTeamNameFavorite

    fun bindItem(favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        Picasso.get().load(favoriteTeam.teamBadge).into(teamImage)
        teamName.text = favoriteTeam.teamName
        itemView.setOnClickListener { listener(favoriteTeam) }
    }
}