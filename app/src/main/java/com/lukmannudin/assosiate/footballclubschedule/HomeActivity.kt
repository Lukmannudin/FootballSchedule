package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lukmannudin.assosiate.footballclubschedule.TeamMatchView.FavoriteTeamsFragment
import com.lukmannudin.assosiate.footballclubschedule.R.id.*
import com.lukmannudin.assosiate.footballclubschedule.TeamMatchView.LastMatchFragment
import com.lukmannudin.assosiate.footballclubschedule.navigation_view.NavigationMain
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener {item ->
            when (item.itemId){
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
        bottom_navigation.selectedItemId = teams
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    NavigationMain(), NavigationMain::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                .commit()
        }
    }


}
