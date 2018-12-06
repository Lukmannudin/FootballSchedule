package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        vpFavorites.adapter = FavoritesFragmentAdapter(supportFragmentManager!!)
        tlFavorites.setupWithViewPager(vpFavorites)
    }
}
