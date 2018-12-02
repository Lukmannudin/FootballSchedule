package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val playerName = intent.getStringExtra(PlayerUtils.PLAYER_NAME)
        val playerBadge = intent.getStringExtra(PlayerUtils.PLAYER_BADGE)
        val playerWeight = intent.getStringExtra(PlayerUtils.PLAYER_WEIGHT)
        val playerHeight = intent.getStringExtra(PlayerUtils.PLAYER_HEIGHT)
        val playerDesc = intent.getStringExtra(PlayerUtils.PLAYER_DESCRIPTION)

        supportActionBar?.title = playerName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Picasso.get().load(playerBadge).into(imgPlayer)
        WeightPlayer.text = playerWeight
        HeightPlayer.text = playerHeight
        playerDescription.text = playerDesc
    }
}
