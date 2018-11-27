package com.lukmannudin.assosiate.footballclubschedule.navigation_view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukmannudin.assosiate.footballclubschedule.FragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.R
import kotlinx.android.synthetic.main.match_main_view.*

class NavigationMain : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_main_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpSchedule.adapter = FragmentAdapter(childFragmentManager!!)
        tlSchedule.setupWithViewPager(vpSchedule)
    }
}