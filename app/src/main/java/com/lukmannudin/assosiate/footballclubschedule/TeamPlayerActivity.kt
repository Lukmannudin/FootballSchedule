package com.lukmannudin.assosiate.footballclubschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lukmannudin.assosiate.footballclubschedule.Adapter.PlayerAdapter
import com.lukmannudin.assosiate.footballclubschedule.Model.Players
import kotlinx.android.synthetic.main.activity_team_player.*

class TeamPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_player)

    }
}
