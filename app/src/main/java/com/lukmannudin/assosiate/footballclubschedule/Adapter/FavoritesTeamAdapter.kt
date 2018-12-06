package com.lukmannudin.assosiate.footballclubschedule.Adapter

import android.database.sqlite.SQLiteConstraintException
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.lukmannudin.assosiate.footballclub.database.database
import com.lukmannudin.assosiate.footballclubschedule.Favorite
import com.lukmannudin.assosiate.footballclubschedule.FavoriteTeam
import com.lukmannudin.assosiate.footballclubschedule.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.favorite_team.view.*
import org.jetbrains.anko.db.delete
import java.nio.file.Files.delete

class FavoritesTeamAdapter(private val favoriteTeam: List<FavoriteTeam>,
                           private val listener: (FavoriteTeam) -> Unit,
                           private val listener2: (FavoriteTeam) -> Unit
                           ) :
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
        holder.bindItem(favoriteTeam[position], listener,listener2)
    }

}


class FavoritesTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamImage: ImageView = view.imgTeamFavorite
    private val teamName: TextView = view.tvTeamNameFavorite
    private val btnDelet: ImageView = view.deleteBtn

    fun bindItem(favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit, listener2: (FavoriteTeam) -> Unit) {
        Picasso.get().load(favoriteTeam.teamBadge).into(teamImage)
        teamName.text = favoriteTeam.teamName
        btnDelet.setOnClickListener { listener2(favoriteTeam) }
        itemView.setOnClickListener { listener(favoriteTeam) }
    }
}