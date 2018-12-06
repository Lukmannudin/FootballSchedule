package com.lukmannudin.assosiate.footballclubschedule.navigation_view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Adapter.ScheduleAdapter
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import com.lukmannudin.assosiate.footballclubschedule.FavoritesFragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.FragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.Presenter.SchedulePresenter
import com.lukmannudin.assosiate.footballclubschedule.R
import com.lukmannudin.assosiate.footballclubschedule.TeamMatchListActivity
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.match_fragment.view.*
import kotlinx.android.synthetic.main.match_main_view.*
import kotlinx.android.synthetic.main.match_main_view.view.*
import kotlinx.android.synthetic.main.team_list.*
import org.jetbrains.anko.support.v4.startActivity

class NavigationFavorites : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_favorites, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpFavorites.adapter = FavoritesFragmentAdapter(childFragmentManager)
        tlFavorites.setupWithViewPager(vpFavorites)
    }

}