package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewpager_main.adapter = FragmentAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
    }

//    companion object {
//        val dataScheduleIntent: String  = "dataSchedule"
//        val teamSchedule: String = "teamSchedule"
//        val teamHomeName: String = "teamHomeName"
//        val teamAwayName: String = "teamAwayName"
//        val teamHomeGoal: String = "teamHomeGoal"
//        val teamAwayGoal: String = "teamAwayGoal"
//        val teamHomeShoot: String  = "teamHomeShoot"
//        val teamAwayShoot: String = "teamAwayShoot"
//        val teamHomeGoalKeeper:String = "teamHomeGoalKeeper"
//        val teamAwayGoalKeeper:String = "teamAwayGoalKeeper"
//        val teamHomeDefense: String = "teamHomeDefense"
//        val teamAwayDefense: String = "teamAwayDefense"
//        val teamHomeMidfield:String = "teamHomeMidfield"
//        val teamAwayMidfield:String = "teamAwayMidfield"
//        val teamHomeForward:String="teamHomeForward"
//        val teamAwayForward:String="teamAwayForward"
//        val teamHomeSubstitutes:String="teamHomeSubstitues"
//        val teamAwaySubstitutes:String ="teamAwaySubstitutes"
//        val teamHomeGoalDetail:String ="teamHomeGoalDetail"
//        val teamAwayGoalDetail:String ="teamAwayGoalDetail"
//    }
}
const val dataScheduleIntent: String  = "dataSchedule"


