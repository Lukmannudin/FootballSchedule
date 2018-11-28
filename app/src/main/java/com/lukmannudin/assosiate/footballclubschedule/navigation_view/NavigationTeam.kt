package com.lukmannudin.assosiate.footballclubschedule.navigation_view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukmannudin.assosiate.footballclubschedule.Adapter.TeamFragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.FragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.R
import kotlinx.android.synthetic.main.match_main_view.*
import kotlinx.android.synthetic.main.team_main.*


class NavigationTeam : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.team_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpTeam.adapter = TeamFragmentAdapter(fragmentManager!!)
        tlTeam.setupWithViewPager(vpTeam)
    }
}