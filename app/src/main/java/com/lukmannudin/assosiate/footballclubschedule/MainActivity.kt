package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        val teamSchedule: String = "teamSchedule"
    }

    private val idTeam: String = "teamSchedule"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewpager_main.adapter = FragmentAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
    }
}


