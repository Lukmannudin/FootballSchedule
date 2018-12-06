package com.lukmannudin.assosiate.footballclubschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lukmannudin.assosiate.footballclubschedule.Adapter.TeamsAdapter
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamsContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Teams
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamsPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_favorites_team_detail.*

class FavoritesTeamDetailActivity : AppCompatActivity() {
    private var teams: MutableList<Teams> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private var intentData: FavoriteTeam? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites_team_detail)

    }


}
