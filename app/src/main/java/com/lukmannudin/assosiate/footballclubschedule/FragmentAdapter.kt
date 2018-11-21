package com.lukmannudin.assosiate.footballclubschedule

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.lukmannudin.assosiate.footballclubschedule.Fragment.FavoriteTeamsFragment
import com.lukmannudin.assosiate.footballclubschedule.Fragment.NextMatchFragment
import com.lukmannudin.assosiate.footballclubschedule.Fragment.TeamsFragment

class FragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    // sebuah list yang menampung objek Fragment
    private val pages: List<Fragment> = listOf(
        TeamsFragment(),
        NextMatchFragment(),
        FavoriteTeamsFragment()
//        ThirdFragment()
    )

    //menentukan fragment yang akan dibuka pada posisi tertentu
    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
//        return super.getPageTitle(position)
        return when(position){
            0 -> "LAST MATCH"
            1 -> "NEXT MATCH"
             else -> {
                "FAVORITES"
            }
        }
    }

}