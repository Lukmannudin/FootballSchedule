package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.lukmannudin.assosiate.footballclubschedule.R.id.*
import com.lukmannudin.assosiate.footballclubschedule.TeamsView.TeamsFragment
import com.lukmannudin.assosiate.footballclubschedule.navigation_view.NavigationFavorites
import com.lukmannudin.assosiate.footballclubschedule.navigation_view.NavigationMain
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val item_id = intent.getStringExtra("itemid")

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                matches -> {
                    loadMatchesFragment(savedInstanceState)
                }
                teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        if (item_id != null) {
            bottom_navigation.selectedItemId = favorites
        } else {
            bottom_navigation.selectedItemId = teams
        }
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    NavigationMain(), NavigationMain::class.java.simpleName
                )
                .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, NavigationFavorites(), NavigationFavorites::class.java.simpleName)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)

    }



}
