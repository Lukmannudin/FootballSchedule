package com.lukmannudin.assosiate.footballclubschedule.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.lukmannudin.assosiate.footballclubschedule.Model.Teams
import com.lukmannudin.assosiate.footballclubschedule.TeamsView.OverviewTeamFragment
import com.lukmannudin.assosiate.footballclubschedule.TeamsView.PlayerListFragment

class TeamFragmentAdapter(fm: FragmentManager, dataTeam: Teams?) : FragmentPagerAdapter(fm) {

    // sebuah list yang menampung objek Fragment
    private val pages: List<Fragment> = listOf(
        OverviewTeamFragment.newInstance(dataTeam),
        PlayerListFragment.newInstance(dataTeam)
    )

    //menentukan fragment yang akan dibuka pada posisi tertentu
    override fun getItem(position: Int): Fragment? {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
//        return super.getPageTitle(position)
        return when (position) {
            0 -> "OVERVIEW"
            else -> {
                "PLAYERS"
            }
        }
    }

}